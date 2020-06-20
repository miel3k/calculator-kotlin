package com.miel3k.calculator.view

import javafx.scene.text.FontWeight
import tornadofx.*

class CalculatorStyles : Stylesheet() {
    init {
        Companion.label {
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            backgroundColor += c("#cecece")
        }
    }
}