package com.luoyuer

import com.luoyuer.player.Player
import com.luoyuer.player.song.impl.LocalSong
import com.luoyuer.player.song.impl.NetSong

fun main() {
    val player = Player()
//    player.addSong(LocalSong("audio/Gifty - 心形纪念/Gifty - 心形纪念.mp3","audio/Gifty - 心形纪念/Gifty - 心形纪念.lrc"))
    player.addSong(NetSong("1487282121", NetSong.NetSongFilter.ID, NetSong.NetSongType.NETEASE))
}