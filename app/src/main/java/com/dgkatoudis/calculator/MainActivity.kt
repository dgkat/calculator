package com.dgkatoudis.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dgkatoudis.calculator.presentation.composables.CalculatorScreen
import com.dgkatoudis.calculator.ui.theme.CalculatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                CalculatorScreen()
            }
        }
    }
}