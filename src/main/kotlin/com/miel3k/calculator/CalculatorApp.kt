package com.miel3k.calculator

import com.miel3k.calculator.view.CalculatorView
import javafx.stage.Stage
import tornadofx.*

class CalculatorApp : App(CalculatorView::class) {

    override fun start(stage: Stage) {
        importStylesheet("/style.css")
        stage.isResizable = false
        super.start(stage)
    }
}