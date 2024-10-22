package com.luoyuer.util

public fun ByteArray.readLEI32(): Int {
    require(size >= 4) { "ByteArray must be at least 4 bytes long" }
    return (this[0].toInt() and 0xFF) or
            ((this[1].toInt() and 0xFF) shl 8) or
            ((this[2].toInt() and 0xFF) shl 16) or
            ((this[3].toInt() and 0xFF) shl 24)
}

public fun ByteArray.readLEI16(): Int {
    require(size >= 2) { "ByteArray must be at least 2 bytes long" }
    return (this[0].toInt() and 0xFF) or
            ((this[1].toInt() and 0xFF) shl 8)
}

public fun ByteArray.readBEI16(): Int {
    require(size >= 2) { "ByteArray must be at least 2 bytes long" }
    return ((this[0].toInt() and 0xFF) shl 8) or
            (this[1].toInt() and 0xFF)
}

public fun ByteArray.readBEI32(): Int {
    require(size >= 4) { "ByteArray must be at least 4 bytes long" }
    return ((this[0].toInt() and 0xFF) shl 24) or
            ((this[1].toInt() and 0xFF) shl 16) or
            ((this[2].toInt() and 0xFF) shl 8) or
            (this[3].toInt() and 0xFF)
}