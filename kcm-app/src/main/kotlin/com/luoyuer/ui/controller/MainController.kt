package com.luoyuer.ui.controller

import javafx.beans.property.SimpleIntegerProperty
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Label

class MainController {


    @FXML
    lateinit var counter: Label

    private val simpleIntegerProperty = SimpleIntegerProperty(0)

    fun initialize() {
        simpleIntegerProperty.addListener { _, _, newValue ->
            counter.text = "Count: $newValue"
        }
    }

    fun onBtnClick(actionEvent: ActionEvent) {
        simpleIntegerProperty.value++
    }
}
