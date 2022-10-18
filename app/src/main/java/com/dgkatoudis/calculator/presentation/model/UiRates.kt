package com.dgkatoudis.calculator.presentation.model

data class UiRates(
    val baseCurrency : String,
    val rates : List<UiCurrencyWithRate>
)