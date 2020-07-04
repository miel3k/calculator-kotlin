package com.miel3k.calculator.controller

import com.miel3k.calculator.model.Component
import java.util.*

class Rpn(private val input: List<Component.Calculable>) {

    fun calculate(): Int? {
        try {
            val output = convertToPostfix()
            val stack = Stack<Int>()
            output.forEach {
                if (it is Component.Calculable.Operator) {
                    val x = stack.pop()
                    val y = stack.pop()
                    stack.push(it.calculate(x, y))
                } else {
                    stack.push(it.symbol.toInt())
                }
            }
            return stack.pop()
        } catch (arithmeticException: ArithmeticException) {
            return null
        }
    }

    private fun convertToPostfix(): Stack<Component.Calculable> {
        val operators = Stack<Component.Calculable.Operator>()
        val output = Stack<Component.Calculable>()
        input.forEach {
            when {
                it is Component.Calculable.Operator && operators.isEmpty() -> operators.push(it)
                it is Component.Calculable.Operator -> evaluateOperator(it, operators, output)
                else -> output.push(it)
            }
        }
        while (!operators.isEmpty()) {
            output.push(operators.pop())
        }
        return output
    }

    private fun evaluateOperator(
        operator: Component.Calculable.Operator,
        operators: Stack<Component.Calculable.Operator>,
        output: Stack<Component.Calculable>
    ) {
        if (operator.priority <= operators.peek().priority) {
            pushOperatorsWithHigherPriority(operator.priority, operators, output)
        }
        operators.push(operator)
    }

    private fun pushOperatorsWithHigherPriority(
        operatorPriority: Int,
        operators: Stack<Component.Calculable.Operator>,
        output: Stack<Component.Calculable>
    ) {
        var isPriorityHigher = true
        while (isPriorityHigher) {
            if (operators.isEmpty()) break
            if (operators.peek().priority >= operatorPriority) {
                output.push(operators.pop())
            } else {
                isPriorityHigher = false
            }
        }
    }
}