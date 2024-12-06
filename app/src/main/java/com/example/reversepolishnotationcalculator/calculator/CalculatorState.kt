package com.example.reversepolishnotationcalculator.calculator

import androidx.compose.ui.text.input.TextFieldValue

data class CalculatorState(
    val input: TextFieldValue,
    val result: RpnResult?
)
