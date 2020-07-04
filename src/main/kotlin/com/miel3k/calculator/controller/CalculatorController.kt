package com.miel3k.calculator.controller

import com.miel3k.calculator.model.Component
import com.miel3k.calculator.view.CalculatorView
import tornadofx.*

class CalculatorController : Controller() {

    private val view by inject<CalculatorView>()
    private val calculableComponents = mutableListOf<Component.Calculable>(
        Component.Calculable.Number.Zero
    )
    private val formula
        get() = calculableComponents.joinToString(EMPTY) { it.symbol }
    private var displayText = Component.Calculable.Number.Zero.symbol
        set(value) {
            field = value
            view.displayText(field)
        }

    fun onComponentClick(component: Component) {
        when (component) {
            is Component.Calculable.Operator -> onOperatorClick(component)
            is Component.Calculable.Number -> onNumberClick(component)
            is Component.Clear -> onClearClick()
            is Component.Equal -> onEqualClick()
        }
    }

    private fun onOperatorClick(component: Component.Calculable.Operator) {
        if (calculableComponents.isEmpty()) return
        if (calculableComponents.lastOrNull() is Component.Calculable.Operator) {
            calculableComponents.removeAt(calculableComponents.size - 1)
        }
        calculableComponents.add(component)
        displayText = formula
    }

    private fun onNumberClick(component: Component.Calculable.Number) {
        when (val lastElement = calculableComponents.lastOrNull()) {
            is Component.Calculable.Number.Zero -> {
                calculableComponents.removeAt(calculableComponents.size - 1)
                calculableComponents.add(component)
            }
            is Component.Calculable.Number -> {
                calculableComponents.removeAt(calculableComponents.size - 1)
                val customNumber =
                    Component.Calculable.Number.Custom(lastElement.symbol + component.symbol)
                calculableComponents.add(customNumber)
            }
            else -> calculableComponents.add(component)
        }
        displayText = formula
    }

    private fun onClearClick() {
        calculableComponents.clear()
        calculableComponents.add(Component.Calculable.Number.Zero)
        displayText = formula
    }

    private fun onEqualClick() {
        if (calculableComponents.isEmpty()
            || calculableComponents.lastOrNull() is Component.Calculable.Operator
        ) {
            return
        }
        displayText = Rpn(calculableComponents).calculate()?.toString() ?: NOT_NUMBER
        calculableComponents.clear()
    }

    private companion object {
        const val EMPTY = ""
        const val NOT_NUMBER = "Not number"
    }
}