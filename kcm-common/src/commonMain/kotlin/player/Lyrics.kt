package com.luoyuer.player

import kotlin.js.JsExport

@JsExport
public class Lyrics(
    public val range: IntRange,
    public val text: String,
) {

    public companion object {

        public val EMPTY: Lyrics = Lyrics(0..0, "EMPTY")
    }
}
