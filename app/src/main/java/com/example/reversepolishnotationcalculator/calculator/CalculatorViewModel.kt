package com.example.reversepolishnotationcalculator.calculator

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.example.reversepolishnotationcalculator.calculator.rpn.RpnCalculationsImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalculatorViewModel : ViewModel() {
    private val rpnCalculations = RpnCalculationsImpl()
    private val _state = MutableStateFlow(
        CalculatorState(
            input = TextFieldValue(),
            result = null
        )
    )
    val state: StateFlow<CalculatorState> = _state.asStateFlow()

    fun updateInput(newInput: String) {
        val space = if (
            newInput != " " &&
            _state.value.input.text.isNotEmpty() &&
            shouldAddSpace(newInput.isDigitsOnly())
        ) {
            " "
        } else ""
        val updatedInput = _state.value.input.text + space + newInput
        updateInputValue(updatedInput)
    }

    fun backspaceInput() {
        val updatedInput = _state.value.input.text.dropLast(1)
        updateInputValue(updatedInput)
    }

    fun clearInput() {
        _state.value = _state.value.copy(
            input = TextFieldValue(),
            result = null
        )
    }

    fun runExpression() {
        val result = rpnCalculations.calculateExpression(_state.value.input.text)
        _state.value = _state.value.copy(result = result)
    }

    private fun updateInputValue(updatedInput: String) {
        _state.value = _state.value.copy(
            input = TextFieldValue(
                text = updatedInput,
                selection = TextRange(updatedInput.length)
            )
        )
    }

    private fun shouldAddSpace(isNewCharADigit: Boolean): Boolean {
        val lastValue = _state.value.input.text.last()
        return lastValue != ' ' &&
                ((isNewCharADigit && !lastValue.isDigit()) || !isNewCharADigit)
    }
}