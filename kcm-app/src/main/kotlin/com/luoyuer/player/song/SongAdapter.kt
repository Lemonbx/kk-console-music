package com.luoyuer.player.song

import com.luoyuer.player.lrc.Lrc
import org.jaudiotagger.audio.AudioFile
import java.io.InputStream

interface SongAdapter {
    /**
     * 返回歌曲流
     */
    fun getInputStream(): InputStream

    /**
     * 歌词信息
     */
    fun getLrc(): Lrc

    /**
     * 歌曲信息
     */
    fun getAudioInfo(): AudioFile
}