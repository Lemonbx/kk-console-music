package com.luoyuer

import com.luoyuer.views.IndexView
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class KCMApplication : Application() {

    override fun start(stage: Stage) {
        val loader = FXMLLoader(javaClass.classLoader.getResource("layout/main.fxml"))
        stage.scene = Scene(loader.load())
        stage.show()
    }
}

fun main(args: Array<String>) {
    Application.launch(KCMApplication::class.java,*args)
//    val player = Player()
//    player.addSong(LocalSong("audio/Gifty - 心形纪念/Gifty - 心形纪念.mp3","audio/Gifty - 心形纪念/Gifty - 心形纪念.lrc"))
//     player.addSong(NetSong("28188425", NetSong.NetSongFilter.ID, NetSong.NetSongType.NETEASE))
    // player.addSong(NetSong("1487282121", NetSong.NetSongFilter.ID, NetSong.NetSongType.NETEASE))
}