package com.luoyuer.ui.controller

import javafx.beans.property.SimpleDoubleProperty
import javafx.fxml.FXML
import javafx.scene.control.Slider
import javafx.scene.layout.HBox

class BottomBarController {

    @FXML
    lateinit var bottomSlider: Slider

    @FXML
    lateinit var bottomHBox: HBox

    private val progress = SimpleDoubleProperty(0.0)

    fun initialize() {

        bottomSlider.prefWidthProperty().bind(bottomHBox.widthProperty().multiply(0.7))

        // track color
        bottomSlider.valueProperty().addListener { _, _, newValue ->
            val progress = newValue.toDouble() / bottomSlider.max * 100
            val style = "-fx-background-color: linear-gradient(to right, -kk-primary-700 $progress%, -kk-primary-200 $progress%);"
            bottomSlider.lookup(".track").style = style
        }
        bottomSlider.valueProperty().bindBidirectional(progress)

    }
}