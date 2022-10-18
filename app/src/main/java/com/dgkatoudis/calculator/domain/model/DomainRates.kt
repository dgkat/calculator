package com.dgkatoudis.calculator.domain.model

data class DomainRates(
    val baseCurrency: String,
    val rates: List<DomainCurrencyWithRate>
)
