package com.dgkatoudis.calculator.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dgkatoudis.calculator.presentation.CalculatorEvent
import com.dgkatoudis.calculator.presentation.model.UiCurrencyWithRate

@Composable
fun CurrencyListBox(
    modifier: Modifier = Modifier,
    currencyList: MutableList<UiCurrencyWithRate>,
    index: Int,
    onClick: (CalculatorEvent) -> Unit = {}
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = .5f))
            .clickable {
                onClick(CalculatorEvent.SwitchCurrencyList(-1))
            }
    ) {
        Box(
            modifier = modifier
                .align(Alignment.Center)
                .requiredSize(height = 400.dp, width = 250.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.Black)
        ) {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(currencyList) { _, currency ->
                    CurrencyText(
                        text = currency.currency,
                        currencyBoxIndex = index,
                        onClick = onClick
                    )
                }
            }
            Box(
                modifier = modifier
                    .align(Alignment.BottomCenter)
                    .background(Color.Black)
            ) {
                Text(text = "Cancel",
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            onClick(CalculatorEvent.SwitchCurrencyList(-1))
                        }
                )
            }
        }
    }
}

@Composable
fun CurrencyText(
    modifier: Modifier = Modifier,
    text: String = "",
    currencyBoxIndex: Int = 0,
    onClick: (CalculatorEvent) -> Unit = {}
) {
    Text(
        text = text,
        fontSize = 24.sp,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(CalculatorEvent.CurrencyPick(text, currencyBoxIndex))
            }
    )

}

@Composable
fun CurrencyRow(
    modifier: Modifier = Modifier,
    text: String = "Unknown",
    index: Int,
    number: String = "1",
    onClick: (CalculatorEvent) -> Unit = {}

) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(100.dp)
    ) {
        Box(
            modifier = modifier
                .align(Alignment.TopStart)
                .clip(CircleShape)
                .background(Color.Black)
                .clickable { onClick(CalculatorEvent.SwitchCurrencyList(index)) }
        ) {
            Text(
                text = text,
                modifier = modifier.padding(vertical = 16.dp, horizontal = 24.dp)
            )
        }
        Text(
            text = number.toString(),
            modifier = modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        )
    }
}
