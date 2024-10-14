package com.luoyuer.player

public sealed class LrcTag(
    public val tagName: String,
    public val value: String,
) {

    /**
     * 	Title of the song
     */
    public class Ti(value: String) : LrcTag("ti", value)

    /**
     * 	Artist performing the song
     */
    public class Ar(value: String) : LrcTag("ar", value)

    /**
     * 	Album the song is from
     */
    public class Al(value: String) : LrcTag("al", value)

    /**
     * Author of the song
     */
    public class Auto(value: String) : LrcTag("au", value)

    /**
     * Length of the song (mm:ss)
     */
    public class Length(value: String) : LrcTag("length", value) {
        public val lenInMils: Int = TODO()
    }

    /**
     * 	Author of the LRC file (not the song)
     */
    public class By(value : String) : LrcTag("by", value)

    /**
     * Specifies a global offset value for the lyric times, in milliseconds. The value is prefixed with either + or -, with + causing lyrics to appear sooner
     */
    public class Offset(value: String) : LrcTag("offset", value)

    /**
     * The player or editor that created the LRC file
     */
    public class Re(value : String) : LrcTag("re", value)

    /**
     * The player or editor that created the LRC file
     */
    public class Tool(value: String) : LrcTag("tool", value)

    /**
     * The version of the program
     */
    public class Ve(value: String) : LrcTag("ve", value)

    /**
     * Comments
     */
    public class Comments(value: String) : LrcTag("#", value)

    override fun toString(): String {
        return "[$tagName: $value]"
    }

}