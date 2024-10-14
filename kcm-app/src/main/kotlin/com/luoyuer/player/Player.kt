package com.luoyuer.player

import com.luoyuer.player.song.SongAdapter
import org.jaudiotagger.audio.AudioFile
import org.jaudiotagger.tag.FieldKey
import kotlin.concurrent.thread
import javazoom.jl.player.Player as JLayer

class Player {
    private var current: Int = 0
    private val playList = ArrayList<SongAdapter>()
    private var state: State = State.STOPPED
    private var playThread: Thread? = null
    private var lrcThread: Thread? = null
    fun addSong(song: SongAdapter) {
        playList.add(song)
        play()
    }
    fun play(){
        if (state == State.PLAYING || playList.isEmpty()) return
        state = State.PLAYING
        playThread = thread{
            while (true){
                //获取当前应该播放的歌曲
                if (current >=playList.size)current = 0
                val song = playList[current]
                current++
                val length = readLength(song.getAudioInfo())
                val lengthTime = numberToTime(length)
                val taskTag = buildTaskName(song.getAudioInfo())
                song.getInputStream().use { stream ->
                    val player = JLayer(stream)
                    //启动歌词线程
                    lrcThread = thread{
                        while (!player.isComplete){
                            print("\r${taskTag} ${numberToTime((player.position/1000).toLong())}/${lengthTime} ${song.getLrc().getText(player.position.toLong())}")
                            try{
                                Thread.sleep(100)
                            }catch (e: Exception){}
                        }
                    }
                    player.play()
                    lrcThread?.interrupt()
                }
            }
        }
    }
    fun stop(){
        if (state == State.STOPPED) return
        state = State.STOPPED
        try{
            playThread?.interrupt()
        }catch (_: Exception){}
        try {
            lrcThread?.interrupt()
        }catch (_: Exception){}
    }
    private fun readLength(fileInfo:AudioFile) = fileInfo.audioHeader?.trackLength?.toLong()?:throw RuntimeException("读取时长失败")
    private fun buildTaskName(fileInfo: AudioFile) = "${fileInfo.tag.getFirst(FieldKey.TITLE)} - ${fileInfo.tag.getFirst(FieldKey.ARTIST)}"
    private fun outLine(text:String){
        print("\r$text")
    }
    private fun numberToTime(time:Long):String{
        var m = time/60
        var s = time-m*60
        return "${m.toString().padStart(2,'0')}:${s.toString().padStart(2,'0')}"
    }

    enum class State {
        PLAYING,
        STOPPED,
    }
}