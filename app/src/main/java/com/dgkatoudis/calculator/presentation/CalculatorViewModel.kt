package com.dgkatoudis.calculator.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dgkatoudis.calculator.domain.model.Resource
import com.dgkatoudis.calculator.domain.usecases.CalculateResult
import com.dgkatoudis.calculator.domain.usecases.GetRatesByCurrency
import com.dgkatoudis.calculator.presentation.mappers.DomainToUiRatesMapper
import com.dgkatoudis.calculator.presentation.model.UiCurrencyWithRate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculateResult: CalculateResult,
    private val domainToUiRatesMapper: DomainToUiRatesMapper,
    private val getRatesByCurrency: GetRatesByCurrency
) : ViewModel() {

    val currencyList = mutableStateListOf<UiCurrencyWithRate>()

    val isCurrencyConverterEnabled = mutableStateOf(false)

    val isCurrencyListEnabled = mutableStateOf(false)

    val number = mutableStateOf("")

    private var canAddOperation = false

    private var canAddDecimal = true

    //To prevent "." after calculate result
    private var calcOccurred = false

    val firstCurrency = mutableStateOf("EUR")

    val secondCurrency = mutableStateOf("EUR")

    var currencyBoxIndex = 0

    var currentExchangeRate = 1.0

    val secondPrice = mutableStateOf("0")

    init {

        getRates(firstCurrency.value)

    }


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

                changeExchangeRate()
                calculateExchange()
            }
            CalculatorEvent.SwitchCurrencyConverterVisibility -> {
                isCurrencyConverterEnabled.value =
                    !isCurrencyConverterEnabled.value

            }
            is CalculatorEvent.SwitchCurrencyList -> {
                isCurrencyListEnabled.value = !isCurrencyListEnabled.value
                currencyBoxIndex = calculatorEvent.index
            }
            is CalculatorEvent.CurrencyPick -> {

                changeCurrency(calculatorEvent.currency)

                isCurrencyListEnabled.value = false
            }
        }
    }

    private fun changeCurrency(currency: String) {
        if (currencyBoxIndex == 0) {
            firstCurrency.value = currency
            getRates(firstCurrency.value)
        } else {
            secondCurrency.value = currency
            changeExchangeRate()
        }
    }

    private fun changeExchangeRate() {
        for (i in 0 until currencyList.size) {
            if (
                currencyList[i].currency == secondCurrency.value
            ) {
                currentExchangeRate = currencyList[i].rate
            }
        }
    }

    private fun calculateExchange() {
        val tempNum = number.value.ifEmpty {
            "0"
        }
        secondPrice.value = (tempNum.toDouble() * currentExchangeRate).toString()
    }

    private fun getRates(currency: String) {
        viewModelScope.launch {
            when (val result = getRatesByCurrency(currency)) {
                is Resource.Success -> {
                    val list = result.data?.let { domainToUiRatesMapper.map(it) }
                    currencyList.addAll(list?.rates ?: emptyList())
                }
                else -> {
                    println("Could not Load")
                }
            }
        }
    }
}