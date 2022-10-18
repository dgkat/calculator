package com.dgkatoudis.calculator.presentation

sealed class CalculatorEvent {
    object AllClear : CalculatorEvent()
    object Delete : CalculatorEvent()
    object Decimal : CalculatorEvent()
    object Calculate : CalculatorEvent()
    object SwitchCurrencyConverterVisibility : CalculatorEvent()
    data class SwitchCurrencyList(val index : Int) : CalculatorEvent()
    data class Number(val number: String) : CalculatorEvent()
    data class Operation(val operation: CalculatorOperation) : CalculatorEvent()
    data class CurrencyPick(val currency:String,val index: Int) : CalculatorEvent()
}
