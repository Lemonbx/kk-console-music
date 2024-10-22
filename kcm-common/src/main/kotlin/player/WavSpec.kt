package com.luoyuer.player

import com.luoyuer.util.readLEI16
import com.luoyuer.util.readLEI32
import java.io.RandomAccessFile

public class WavSpec(
    public val fileTypeBlocID: ByteArray, // RIFF
    public val fileSize: Int,
    public val wave: ByteArray, // WAVE

    public val formatBlocID: ByteArray,
    public val blocSize: Int,
    public val audioFormat: Int, // 1: PCM integer, 3: IEEE 754 float
    public val channels: Int,
    public val sampleRate: Int,
    public val bytePerSpec: Int,
    public val bytePerBloc: Int,
    public val bitsPerSample: Int,

    public val dataBlocID: ByteArray, // data
    public val dataSize: Int
) {

    public companion object {

        public val RIFF: ByteArray = byteArrayOf(0x52, 0x49, 0x46, 0x46)
        public val WAVE: ByteArray = byteArrayOf(0x57, 0x41, 0x56, 0x45)
        public val FMT: ByteArray = byteArrayOf(0x66, 0x6d, 0x74, 0x20)
        public val DATA: ByteArray = byteArrayOf(0x64, 0x61, 0x74, 0x61)

        public fun fromFile(rawFile: RandomAccessFile): WavSpec {
            require(rawFile.length() > 44)
            rawFile.seek(0)
            val buffer = ByteArray(4)
            require(rawFile.read(buffer) == 4)
            require(buffer.contentEquals(RIFF)) { "Not RIFF" }
            require(rawFile.read(buffer) == 4)
            val fileSize = buffer.readLEI32()
            require(rawFile.read(buffer) == 4)
            require(buffer.contentEquals(WAVE)) { "Not WAVE" }
            require(rawFile.read(buffer) == 4)
            require(buffer.contentEquals(FMT)) { "Not FMT" }
            require(rawFile.read(buffer) == 4)
            val blocSize = buffer.readLEI32()
            require(rawFile.read(buffer) == 4)
            val audioFormat = buffer.readLEI16()
            buffer[0] = buffer[3]
            buffer[1] = buffer[2]
            val channels = buffer.readLEI16()
            require(rawFile.read(buffer) == 4)
            val sampleRate = buffer.readLEI32()
            require(rawFile.read(buffer) == 4)
            val bytePerSpec = buffer.readLEI32()
            require(rawFile.read(buffer) == 4)
            val bytePerBloc = buffer.readLEI16()
            buffer[0] = buffer[3]
            buffer[1] = buffer[2]
            val bitsPerSample = buffer.readLEI16()
            require(rawFile.read(buffer) == 4)
            require(buffer.contentEquals(DATA)) { "Not DATA" }
            require(rawFile.read(buffer) == 4)
            val dataSize = buffer.readLEI32()
            return WavSpec(
                fileTypeBlocID = RIFF.copyOf(),
                fileSize = fileSize,
                wave =  WAVE.copyOf(),
                formatBlocID = FMT.copyOf(),
                blocSize = blocSize,
                audioFormat = audioFormat,
                channels = channels,
                sampleRate = sampleRate,
                bytePerSpec = bytePerSpec,
                bytePerBloc = bytePerBloc,
                bitsPerSample = bitsPerSample,
                dataBlocID = DATA.copyOf(),
                dataSize = dataSize
            )
        }
    }
}
