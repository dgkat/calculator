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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dgkatoudis.calculator.presentation.CalculatorEvent
import com.dgkatoudis.calculator.presentation.CalculatorOperation
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
    }

}

@Composable
fun Calculator(
    modifier: Modifier = Modifier,
    text: String,
    onClick: (CalculatorEvent) -> Unit = {}
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(alignment = Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                text = text.ifEmpty {
                    "0"
                },
                fontSize = 32.sp,
                color = MaterialTheme.colors.onPrimary,
                textAlign = TextAlign.End
            )

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(
                    text = "AC",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(2f),
                    onClick = { onClick(CalculatorEvent.AllClear) }
                )

                CalculatorButton(
                    text = "Del",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Delete) }
                )
                CalculatorButton(
                    text = "/",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Operation(CalculatorOperation.Divide)) }
                )

            }

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(
                    text = "7",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Number("7")) }
                )
                CalculatorButton(
                    text = "8",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Number("8")) }
                )
                CalculatorButton(
                    text = "9",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Number("9")) }
                )
                CalculatorButton(
                    text = "*",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Operation(CalculatorOperation.Multiply)) }
                )
            }

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(
                    text = "4",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Number("4")) }
                )
                CalculatorButton(
                    text = "5",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Number("5")) }
                )
                CalculatorButton(
                    text = "6",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Number("6")) }
                )
                CalculatorButton(
                    text = "-",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Operation(CalculatorOperation.Subtract)) }
                )
            }

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(
                    text = "1",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Number("1")) }
                )
                CalculatorButton(
                    text = "2",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Number("2")) }
                )
                CalculatorButton(
                    text = "3",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Number("3")) }
                )
                CalculatorButton(
                    text = "+",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Operation(CalculatorOperation.Add)) }
                )
            }

            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CalculatorButton(
                    text = "0",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(2f),
                    onClick = { onClick(CalculatorEvent.Number("0")) }
                )
                CalculatorButton(
                    text = ".",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Decimal) }
                )
                CalculatorButton(
                    text = "=",
                    modifier = modifier
                        .background(color = Color.DarkGray)
                        .weight(1f),
                    onClick = { onClick(CalculatorEvent.Calculate) }
                )
            }
        }
    }

}


@Composable
fun CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
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
