package com.luoyuer.player.lrc

import com.luoyuer.player.lrc.internal.LrcParser

public class Lrc(
    lrcStr: String,
    doParse: Boolean = true
) {

    private var lrcStr: String? = lrcStr
    private var data: LrcSpec? = null

    init {
        if (doParse) {
            parse()
            this.lrcStr = null
        }
    }

    public fun parse() {
        val lrcStr = this.lrcStr
            ?: throw IllegalStateException("Lrc already parsed")
        this.data = LrcParser.parse(lrcStr)
        this.lrcStr = null
    }

    public fun getLyrics(mills: Int): Lyrics {
        val spec = checkDataNotNull(data)
        if (spec.lyrics.isEmpty()) {
            return Lyrics.EMPTY
        }
        for (i in spec.lyrics.indices) {
            val lyric = spec.lyrics[i]
            if (i == spec.lyrics.lastIndex) {
                return lyric
            }
            if (mills < lyric.ts) {
                return if (i == 0) {
                    lyric
                } else {
                    spec.lyrics[i - 1]
                }
            }
        }
        return spec.lyrics.last()
    }

    private fun checkDataNotNull(spec: LrcSpec?): LrcSpec {
        if (spec == null) {
            throw IllegalStateException("Lrc spec not parsed")
        }
        return spec
    }
}

public val emptyLrc: Lrc = Lrc("", false)