package com.dgkatoudis.calculator.presentation

sealed class CalculatorEvent {
    object AllClear : CalculatorEvent()
    object Delete : CalculatorEvent()
    object Decimal : CalculatorEvent()
    object Calculate : CalculatorEvent()
    data class Number(val number: String) : CalculatorEvent()
    data class Operation(val operation: CalculatorOperation) : CalculatorEvent()
}
