package com.dgkatoudis.calculator.presentation.mappers

import com.dgkatoudis.calculator.domain.model.DomainCurrencyWithRate
import com.dgkatoudis.calculator.presentation.model.UiCurrencyWithRate

class DomainToUiCurrencyWithRateMapper {
    fun map(domainCurrencyWithRate: DomainCurrencyWithRate): UiCurrencyWithRate {
        return UiCurrencyWithRate(
            currency = domainCurrencyWithRate.currency,
            rate = domainCurrencyWithRate.rate
        )
    }
}