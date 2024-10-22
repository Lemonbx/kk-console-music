package com.luoyuer.player

import java.io.File
import kotlin.properties.Delegates

public abstract class KKongBuilder {


    public abstract fun build(): Song
}

public class FileSongBuilder : KKongBuilder() {

    public var file: File by Delegates.notNull()

    override fun build(): Song {
        TODO("Not yet implemented")
    }
}

public class UrlSongBuilder : KKongBuilder() {

    public var url: String by Delegates.notNull()

    override fun build(): Song {
        TODO("Not yet implemented")
    }
}

