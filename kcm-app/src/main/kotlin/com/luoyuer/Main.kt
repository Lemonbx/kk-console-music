package com.luoyuer

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.stage.Stage
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KProperty

class KCMApplication : Application(), CoroutineScope {

    lateinit var loader: FXMLLoader
        private set

    override val coroutineContext: CoroutineContext
        = SupervisorJob() + Dispatchers.Main + CoroutineName("KCM Application") + kkCoroutineExceptionHandler


    override fun start(stage: Stage) {
        instance = this
        val loader = FXMLLoader(javaClass.classLoader.getResource("layout/main.fxml"))
        this.loader = loader
        loader.setControllerFactory {
            println("Create controller $it")
            it.newInstance()
        }
        stage.scene = Scene(loader.load())
        stage.show()
    }

    companion object {
        lateinit var instance: KCMApplication
        private set

        operator fun getValue(thisRef: Any?, property: KProperty<*>): KCMApplication = instance
    }
}

private val kkCoroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
    val alert = Alert(Alert.AlertType.ERROR)
    alert.title = "Error"
    alert.headerText = throwable.toString()
    alert.contentText = throwable.stackTraceToString()
    alert.show()
}

fun main(args: Array<String>) {
    Application.launch(KCMApplication::class.java,*args)
//    val player = Player()
//    player.addSong(LocalSong("audio/Gifty - 心形纪念/Gifty - 心形纪念.mp3","audio/Gifty - 心形纪念/Gifty - 心形纪念.lrc"))
//     player.addSong(NetSong("28188425", NetSong.NetSongFilter.ID, NetSong.NetSongType.NETEASE))
    // player.addSong(NetSong("1487282121", NetSong.NetSongFilter.ID, NetSong.NetSongType.NETEASE))
}