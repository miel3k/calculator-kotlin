package com.miel3k.calculator.model

sealed class Component(val symbol: String) {

    sealed class Calculable(symbol: String) : Component(symbol) {
        sealed class Operator(symbol: String, val priority: Int) : Calculable(symbol) {
            abstract fun calculate(x: Int, y: Int): Int

            object Add : Operator("+", 1) {
                override fun calculate(x: Int, y: Int) = x + y
            }

            object Sub : Operator("-", 1) {
                override fun calculate(x: Int, y: Int) = y - x
            }

            object Mul : Operator("*", 2) {
                override fun calculate(x: Int, y: Int) = x * y
            }

            object Div : Operator("/", 2) {
                override fun calculate(x: Int, y: Int) = y / x
            }
        }

        sealed class Number(symbol: String) : Calculable(symbol) {
            object Zero : Number("0")
            object One : Number("1")
            object Two : Number("2")
            object Three : Number("3")
            object Four : Number("4")
            object Five : Number("5")
            object Six : Number("6")
            object Seven : Number("7")
            object Eight : Number("8")
            object Nine : Number("9")
            class Custom(symbol: String) : Number(symbol)
        }
    }

    object Clear : Component("C")
    object Equal : Component("=")

    companion object {
        fun from(symbol: String): Component? {
            return when (symbol) {
                Calculable.Operator.Add.symbol -> Calculable.Operator.Add
                Calculable.Operator.Sub.symbol -> Calculable.Operator.Sub
                Calculable.Operator.Mul.symbol -> Calculable.Operator.Mul
                Calculable.Operator.Div.symbol -> Calculable.Operator.Div
                Calculable.Number.Zero.symbol -> Calculable.Number.Zero
                Calculable.Number.One.symbol -> Calculable.Number.One
                Calculable.Number.Two.symbol -> Calculable.Number.Two
                Calculable.Number.Three.symbol -> Calculable.Number.Three
                Calculable.Number.Four.symbol -> Calculable.Number.Four
                Calculable.Number.Five.symbol -> Calculable.Number.Five
                Calculable.Number.Six.symbol -> Calculable.Number.Six
                Calculable.Number.Seven.symbol -> Calculable.Number.Seven
                Calculable.Number.Eight.symbol -> Calculable.Number.Eight
                Calculable.Number.Nine.symbol -> Calculable.Number.Nine
                Clear.symbol -> Clear
                Equal.symbol -> Equal
                else -> null
            }
        }
    }
}