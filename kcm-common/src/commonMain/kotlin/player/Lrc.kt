@file:OptIn(ExperimentalJsExport::class)
@file:JsExport
package com.luoyuer.player

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport
import kotlin.time.Duration

public class Lrc(
    lrcStr: String,
    doParse: Boolean = true
) {

    private var lrcStr: String? = lrcStr

    init {
        if (doParse) {
            parse()
            this.lrcStr = null
        }
    }

    public fun parse() {
        val lrcStr = this.lrcStr
            ?: throw IllegalStateException("Lrc already parsed")
        val lines = lrcStr.split('\n')

        this.lrcStr = null
    }

    public fun getText(duration: Duration): String {
        TODO()
    }
}