@file:OptIn(ExperimentalJsExport::class)
@file:JsExport
package com.luoyuer.player

import com.luoyuer.player.internal.LrcParser
import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

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

    public fun getLyrics(mils: Int): Lyrics {
        val spec = checkDataNotNull(data)
        if (spec.lyrics.isEmpty()) {
            return Lyrics.EMPTY
        }
        for (lyric in spec.lyrics) {
            if (mils in lyric.range) {
                return lyric
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