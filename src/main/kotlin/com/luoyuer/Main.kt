package com.luoyuer

import com.luoyuer.lrc.Lrc
import javazoom.jl.player.Player
import org.jaudiotagger.audio.AudioFile
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.FieldKey
import java.io.File
import java.io.FileInputStream
import kotlin.concurrent.thread

fun main() {
    val audioFile = File("audio/Gifty - 心形纪念/Gifty - 心形纪念.mp3")
    val fileInfo = AudioFileIO.read(audioFile)
    val lrc = Lrc(File("audio/Gifty - 心形纪念/Gifty - 心形纪念.lrc"))
    val length = readLength(fileInfo)
    val lengthTime = numberToTime(length)
    val taskTag = buildTaskName(fileInfo)
    val player = Player(FileInputStream(audioFile))
    thread {
        player.play()
    }
    thread {
        println()
        while (!player.isComplete){
            outLine("${taskTag} ${numberToTime((player.position/1000).toLong())}/${lengthTime} ${lrc.getText(player.position.toLong())}")
            Thread.sleep(100)
        }
    }
}
fun readLength(fileInfo:AudioFile) = fileInfo.audioHeader?.trackLength?.toLong()?:throw RuntimeException("读取时长失败")
fun buildTaskName(fileInfo: AudioFile) = "${fileInfo.tag.getFirst(FieldKey.TITLE)} - ${fileInfo.tag.getFirst(FieldKey.ARTIST)}"
fun outLine(text:String){
    print("\r$text")
}
fun numberToTime(time:Long):String{
    var m = time/60
    var s = time-m*60
    return "${m.toString().padStart(2,'0')}:${s.toString().padStart(2,'0')}"
}
