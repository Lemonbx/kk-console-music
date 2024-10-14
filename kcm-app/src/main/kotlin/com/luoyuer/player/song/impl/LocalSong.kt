package com.luoyuer.player.song.impl

import com.luoyuer.player.Lrc
import com.luoyuer.player.emptyLrc
import com.luoyuer.player.song.SongAdapter
import org.jaudiotagger.audio.AudioFile
import org.jaudiotagger.audio.AudioFileIO
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

class LocalSong(
    val audioFilePath:String,
    val lrcFilePath:String?
): SongAdapter {
    private var audioFile : File? = null
    private var lrcFile : File? = null
    private var lrc: Lrc = emptyLrc
    private var audioInfo : AudioFile? = null
    init {
        audioFile = File(audioFilePath)
        if(audioFile?.exists() != true){
            throw RuntimeException("File does not exists!")
        }else{
            audioInfo = AudioFileIO.read(audioFile)
        }
        if (lrcFilePath != null) {
            lrcFile = File(lrcFilePath)
            if (lrcFile?.exists() != true){
                System.err.println("LRC File does not exists!")
            }else{
                lrc = Lrc(lrcFile!!.readText(charset("GB2312")))
            }
        }
    }
    override fun getInputStream(): InputStream {
        return FileInputStream(audioFile)
    }

    override fun getLrc(): Lrc {
        return lrc
    }

    override fun getAudioInfo(): AudioFile {
        return audioInfo!!
    }
}