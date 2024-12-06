package com.example.reversepolishnotationcalculator.calculator

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reversepolishnotationcalculator.R
import com.example.reversepolishnotationcalculator.ui.theme.ReversePolishNotationCalculatorTheme

@Composable
fun CalculatorView() {
    val viewModel = CalculatorViewModel()
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CalculatorHeaderSection(viewModel)
        CalculatorButtonsSection(viewModel)
        CalculatorResultSection(viewModel)
    }
}

@Composable
private fun CalculatorHeaderSection(viewModel: CalculatorViewModel) {
    Text(
        text = stringResource(id = R.string.calculator_title),
        style = MaterialTheme.typography.titleLarge
    )
    Text(
        text = stringResource(id = R.string.calculator_subtitle),
        modifier = Modifier.padding(6.dp)
    )
    CalculatorInputField(viewModel)
    Divider(modifier = Modifier.padding(vertical = 6.dp))
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CalculatorInputField(viewModel: CalculatorViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
        keyboardController?.hide()
    }
    Box {
        BasicTextField(
            value = viewModel.state.collectAsState().value.input,
            onValueChange = {  },
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth(),
            singleLine = true,
            textStyle = MaterialTheme.typography.headlineLarge.copy(
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.End
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onSurface)
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .alpha(0f)
                .clickable { /* Cover text field so user can't click it */ }
        )
    }
}

@Composable
private fun CalculatorButtonsSection(viewModel: CalculatorViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CalculatorButton({viewModel.updateInput("7")}, stringResource(id = R.string.seven))
        CalculatorButton({viewModel.updateInput("8")}, stringResource(id = R.string.eight))
        CalculatorButton({viewModel.updateInput("9")}, stringResource(id = R.string.nine))
        CalculatorButton({viewModel.updateInput("+")}, stringResource(id = R.string.plus))
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CalculatorButton({viewModel.updateInput("4")}, stringResource(id = R.string.four))
        CalculatorButton({viewModel.updateInput("5")}, stringResource(id = R.string.five))
        CalculatorButton({viewModel.updateInput("6")}, stringResource(id = R.string.six))
        CalculatorButton({viewModel.updateInput("-")}, stringResource(id = R.string.minus))
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CalculatorButton({viewModel.updateInput("1")}, stringResource(id = R.string.one))
        CalculatorButton({viewModel.updateInput("2")}, stringResource(id = R.string.two))
        CalculatorButton({viewModel.updateInput("3")}, stringResource(id = R.string.three))
        CalculatorButton({viewModel.updateInput("*")}, stringResource(id = R.string.times))
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CalculatorButton({viewModel.updateInput("0")}, stringResource(id = R.string.zero))
        CalculatorButton({viewModel.updateInput(".")}, stringResource(id = R.string.dot))
        CalculatorButton({viewModel.clearInput()}, stringResource(id = R.string.clear))
        CalculatorButton({viewModel.updateInput("/")}, stringResource(id = R.string.divide))
    }
    Button(
        onClick = { viewModel.updateInput(" ") },
        content = {
            Text(
                text = stringResource(id = R.string.space),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
    Button(
        onClick = { viewModel.backspaceInput() },
        content = {
            Text(
                text = stringResource(id = R.string.backspace),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
    Button(
        onClick = { viewModel.runExpression() },
        content = {
            Text(
                text = stringResource(id = R.string.enter),
                style = MaterialTheme.typography.bodyLarge
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun CalculatorButton(
    onClick: () -> Unit,
    label: String
) {
    val width = LocalConfiguration.current.screenWidthDp.dp
    val buttonSize = width / 5
    Button(
        onClick = onClick,
        content = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        modifier = Modifier
            .padding(horizontal = 3.dp)
            .size(buttonSize)
    )
}

@Composable
private fun CalculatorResultSection(viewModel: CalculatorViewModel) {
    val result = viewModel.state.collectAsState().value.result
    if (result != null) {
        val resultText = if (result.error.isNotEmpty()) {
            result.error
        } else {
            result.result.toString()
        }
        Text(
            text = resultText,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    ReversePolishNotationCalculatorTheme {
        CalculatorView()
    }
}