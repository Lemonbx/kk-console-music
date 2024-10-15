package com.luoyuer.views

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.StackPane
import javafx.scene.web.WebView
import javafx.stage.Stage


class IndexView:Application() {
    override fun start(stage: Stage) {
        val webView = WebView()
        val engine = webView.engine
        engine.load("https://kirapzz.moe")
        val scene = Scene(StackPane(webView))
        stage.scene = scene
        stage.show()
    }
}