package com.luoyuer.player.song.impl

import cn.hutool.core.io.FileUtil
import cn.hutool.http.HttpUtil
import cn.hutool.json.JSONUtil
import com.luoyuer.player.Lrc
import com.luoyuer.player.emptyLrc
import com.luoyuer.player.song.SongAdapter
import org.jaudiotagger.audio.AudioFile
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.FieldKey
import org.jaudiotagger.tag.Tag
import org.jaudiotagger.tag.wav.WavInfoTag
import org.jaudiotagger.tag.wav.WavTag
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.InputStream

data class NetSong(
    val input: String,
    val filter: NetSongFilter,
    val type: NetSongType
): SongAdapter{
    private var audioFile : ByteArray? = null
    private var lrc: Lrc = emptyLrc
    private var audioInfo : AudioFile? = null
    init {
        HttpUtil.createPost("https://y.0msl.com/")
            .form("input", input)
            .form("filter", filter.type)
            .form("type",type.type)
            .form("page",1)
            .header("x-requested-with", "XMLHttpRequest")
            .execute().use { response ->
                val entries = JSONUtil.parseObj(response.body())
                if (entries.getInt("code") == 200) {
                    val list = entries.getJSONArray("data")
                    val jsonObject = list.getJSONObject(0)
                    val url = jsonObject.getStr("url")
                    val lrcStr = jsonObject.getStr("lrc").split("\n")
                    audioFile = HttpUtil.downloadBytes(url)
                    val dir = File(".mp3")
                    dir.mkdir()
                    val tempFile = File.createTempFile("tmp", ".mp3", dir)
                    tempFile.createNewFile()
                    FileUtil.writeBytes(audioFile,tempFile)
                    audioInfo = AudioFileIO.read(tempFile)
                    tempFile.delete()
                    audioInfo!!.tag.setField(FieldKey.TITLE,jsonObject.getStr("title"))
                    audioInfo!!.tag.setField(FieldKey.ARTIST,jsonObject.getStr("author"))
                    lrc = Lrc(lrcStr)
                } else {
                    throw RuntimeException("音乐不存在")
                }
            }
    }
    override fun getInputStream(): InputStream {
        return ByteArrayInputStream(audioFile)
    }

    override fun getLrc(): Lrc {
        return lrc
    }

    override fun getAudioInfo(): AudioFile {
        return audioInfo!!
    }

    enum class NetSongType(val type: String) {
        NETEASE("netease"),
        QQ("qq"),
        KUGOU("kugou"),
        KUWO("kuwo"),
        KG("kg")
    }
    enum class NetSongFilter(val type: String) {
        NAME("name"),
        ID("id"),
        URL("url")
    }

}