package com.miel3k.calculator.view

import com.miel3k.calculator.controller.CalculatorController
import com.miel3k.calculator.model.Component
import javafx.scene.control.Label
import javafx.scene.input.KeyEvent
import javafx.scene.layout.HBox
import tornadofx.*

class CalculatorView : View() {

    private val controller by inject<CalculatorController>()
    private var displayLabel by singleAssign<Label>()

    override val root = vbox {
        addClass("calculator")
        displayLabel = label {
            text = Component.Calculable.Number.Zero.symbol
            addClass("display")
        }
        hbox {
            button(Component.Calculable.Number.Seven)
            button(Component.Calculable.Number.Eight)
            button(Component.Calculable.Number.Nine)
            button(Component.Calculable.Operator.Mul)
        }
        hbox {
            button(Component.Calculable.Number.Four)
            button(Component.Calculable.Number.Five)
            button(Component.Calculable.Number.Six)
            button(Component.Calculable.Operator.Div)
        }
        hbox {
            button(Component.Calculable.Number.One)
            button(Component.Calculable.Number.Two)
            button(Component.Calculable.Number.Three)
            button(Component.Calculable.Operator.Add)
        }
        hbox {
            button(Component.Calculable.Number.Zero)
            button(Component.Clear)
            button(Component.Equal)
            button(Component.Calculable.Operator.Sub)
        }
    }

    private fun HBox.button(component: Component) {
        button(component.symbol) {
            addClass(".button")
            setOnMouseClicked {
                controller.onComponentClick(component)
            }
        }
    }

    init {
        root.addClass(".root")
        root.addEventFilter(KeyEvent.KEY_TYPED) { keyEvent ->
            val characterText = keyEvent.character.toUpperCase().replace("\r", "=")
            Component.from(characterText)?.let { controller.onComponentClick(it) }
        }
    }

    fun displayText(newText: String) {
        displayLabel.text = newText
    }
}