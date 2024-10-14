package com.luoyuer.player

import kotlin.js.JsExport

@JsExport
public class Lyrics(
    public val ts: Int,
    public val text: String,
) {

    public companion object {

        public val EMPTY: Lyrics = Lyrics(-1, "EMPTY")
    }
}
