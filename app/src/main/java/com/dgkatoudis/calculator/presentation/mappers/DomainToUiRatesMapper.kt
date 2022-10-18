package com.dgkatoudis.calculator.presentation.mappers

import com.dgkatoudis.calculator.domain.model.DomainRates
import com.dgkatoudis.calculator.presentation.model.UiRates

class DomainToUiRatesMapper(
    private val domainToUiCurrencyWithRateMapper: DomainToUiCurrencyWithRateMapper
) {
    fun map(domainRates: DomainRates): UiRates {
        return UiRates(
            baseCurrency = domainRates.baseCurrency,
            rates = domainRates.rates.map {
                domainToUiCurrencyWithRateMapper.map(it)
            }
        )
    }
}