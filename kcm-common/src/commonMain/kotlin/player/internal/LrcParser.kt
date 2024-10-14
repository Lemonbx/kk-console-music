package com.luoyuer.player.internal

import com.luoyuer.player.LrcSpec
import com.luoyuer.player.LrcTag
import com.luoyuer.player.Lyrics

internal object LrcParser {

    fun parse(str: String): LrcSpec {
        val lines = str.lineSequence()
        var tags: MutableList<LrcTag>? = null
        val lyrics = mutableListOf<Lyrics>()
        var tagFinished = false

        for (line in lines) {
            if (line.isEmpty()) continue
            if (!tagFinished) { // check tags
                if (isTag(line)) {
                    if (tags == null) {
                        tags = mutableListOf()
                    }
                    tags.add(parseTag(line))
                } else {
                    lyrics.add(parseLyrics(line))
                    tagFinished = true
                }
            } else {
                lyrics.add(parseLyrics(line))
            }
        }
        return LrcSpec(tags ?: emptyList(), lyrics)
    }

    private fun parseLyrics(str: String): Lyrics {
        val i1 = str.indexOf(':')
        val i2 = str.indexOf('.')
        val i3 = str.indexOf(']')

        val text = if (str.length > i3 + 1) str.substring(i3 + 1, str.length) else ""
        val t1 = str.substring(1, i1).toInt()
        val t2 = str.substring(i1 + 1, i2).toInt()
        val t3 = try {
            str.substring(i2 + 1, i3).toInt()
        } catch (_: Exception) {
            0
        }
        val mills = t1 * 60 * 1000 + t2 * 1000 + t3 * 10
        return Lyrics(mills, text)
    }

    private fun isTag(str: String): Boolean {
        return str.startsWith("[") && str.endsWith("]") && str.contains(":")
    }

    private fun parseTag(str: String): LrcTag {
        checkTagFormat(str)
        val i = str.indexOf(":")
        val name = str.substring(1, i).trim()
        val value = str.substring(i + 1, str.length - 1).trim()
        return LrcTag.from(name, value)
    }

    private fun checkTagFormat(tagStr: String) {
        require(isTag(tagStr)) {
            "Invalid tag format: $tagStr"
        }
    }

    private fun checkLyrics(lyricsStr: String) {
        require(lyricsStr.startsWith("[") && lyricsStr.endsWith("]")) {
            "Invalid lyrics format: $lyricsStr"
        }
    }
}
