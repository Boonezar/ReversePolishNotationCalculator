package com.example.reversepolishnotationcalculator.calculator.rpn

import com.example.reversepolishnotationcalculator.calculator.RpnResult
import java.util.Stack

class RpnCalculationsImpl : RpnCalculations {
    override fun calculateExpression(expression: String): RpnResult {
        val stack = Stack<Double>()
        val tokens = expression.split(" ")
        var error = ""
        run breaking@ {
            tokens.forEach { token ->
                val number = token.toDoubleOrNull()
                when {
                    number != null -> stack.add(number)
                    token.isEmpty() -> { /* no-op */ }
                    else -> {
                        if (stack.size < 2) {
                            error = "Error: Too Many Operators"
                        } else {
                            val operand2 = stack.pop()
                            val operand1 = stack.pop()
                            val result: Double? = when (token) {
                                "+" -> operand1 + operand2
                                "-" -> operand1 - operand2
                                "*" -> operand1 * operand2
                                "/" -> if (operand2 == 0.0) {
                                    error = "Error: Division by Zero"
                                    null
                                } else {
                                    operand1 / operand2
                                }

                                else -> {
                                    error = "Error: Invalid Operation"
                                    null
                                }
                            }
                            if (result != null) {
                                stack.add(result)
                            }
                        }
                    }
                }
                if (error.isNotEmpty()) {
                    return@breaking
                }
            }
        }
        return when {
            error.isNotEmpty() -> RpnResult(result = 0.0, error = error)
            stack.size != 1 -> RpnResult(result = 0.0, error = "Error: Too Many Numbers")
            else -> RpnResult(result = stack.pop(), error = "")
        }
    }
}