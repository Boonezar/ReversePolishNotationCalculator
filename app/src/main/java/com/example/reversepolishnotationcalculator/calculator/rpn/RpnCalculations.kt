package com.example.reversepolishnotationcalculator.calculator.rpn

import com.example.reversepolishnotationcalculator.calculator.RpnResult

interface RpnCalculations {
    fun calculateExpression(expression: String): RpnResult
}