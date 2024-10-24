package com.luoyuer.player

// TODO
public data class SongSpec(
    val sampleRate: Int, // 44100
)

//歌曲（从本地路径加载或远程路径）
public interface Song {

    public val spec: SongSpec

    public companion object {

        public fun ofFile(block: FileSongBuilder.() -> Unit): Song = FileSongBuilder().apply(block).build()
    }
}

internal object EmptySong : Song {

    override val spec: SongSpec
        get() = TODO("Not yet implemented")
}