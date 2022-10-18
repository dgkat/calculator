package com.dgkatoudis.calculator.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dgkatoudis.calculator.presentation.CalculatorViewModel


@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.primary
    ) {
        Calculator(
            onClick = viewModel::onCalculatorEvent,
            text = viewModel.number.value
        )
        if (viewModel.isCurrencyConverterEnabled.value) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    CurrencyRow(
                        text = viewModel.firstCurrency.value,
                        number = viewModel.number.value,
                        index = 0,
                        onClick = viewModel::onCalculatorEvent
                    )

                    CurrencyRow(
                        text = viewModel.secondCurrency.value,
                        number = viewModel.secondPrice.value,
                        index = 1,
                        onClick = viewModel::onCalculatorEvent
                    )
                }
            }
        }
        if (viewModel.isCurrencyListEnabled.value) {
            CurrencyListBox(
                currencyList = viewModel.currencyList,
                index = viewModel.currencyBoxIndex,
                onClick = viewModel::onCalculatorEvent
            )
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onSurface,
    onClick: () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(36.dp))
            .background(color)
            .clickable {
                onClick()
            }
            .then(modifier)
    ) {
        Text(
            text = text,
            fontSize = 32.sp,
            color = Color.White
        )
    }
}
