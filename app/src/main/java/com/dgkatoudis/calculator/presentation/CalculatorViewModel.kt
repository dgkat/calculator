package com.dgkatoudis.calculator.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dgkatoudis.calculator.domain.usecases.CalculateResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculateResult: CalculateResult
) : ViewModel() {

    val number = mutableStateOf("")

    private var canAddOperation = false

    private var canAddDecimal = true

    //To prevent "." after calculate result
    private var calcOccurred = false

    fun onCalculatorEvent(calculatorEvent: CalculatorEvent) {
        when (calculatorEvent) {
            is CalculatorEvent.AllClear -> {
                number.value = ""
                canAddOperation = false
                canAddDecimal = true
                calcOccurred = false
            }
            is CalculatorEvent.Decimal -> {
                if (!calcOccurred) {
                    if (canAddDecimal) {
                        val prevChar = number.value.takeLast(1)
                        if ((prevChar == "*" || prevChar == "/" || prevChar == "+" || prevChar == "-")) {
                            number.value = number.value + "0."
                        } else {
                            number.value = number.value + "."
                        }
                        canAddDecimal = false
                    }
                }
            }
            is CalculatorEvent.Delete -> {
                val charToDel = number.value.takeLast(1)
                number.value = number.value.dropLast(1)
                if (charToDel == "*" || charToDel == "/" || charToDel == "+" || charToDel == "-") {
                    canAddOperation = true
                } else if (charToDel == ".") {
                    canAddDecimal = true
                    calcOccurred = false
                }
                if (number.value.isEmpty()) {
                    canAddOperation = false
                }
            }
            is CalculatorEvent.Number -> {
                number.value = number.value + calculatorEvent.number
                canAddOperation = true
            }
            is CalculatorEvent.Operation -> {
                if (canAddOperation) {
                    number.value = number.value + calculatorEvent.operation.operation
                    canAddOperation = false
                }
                calcOccurred = false
            }
            CalculatorEvent.Calculate -> {
                calcOccurred = true
                number.value = calculateResult.invoke(number.value)
            }
        }
    }
}