package com.dgkatoudis.calculator.data.mappers

import com.dgkatoudis.calculator.data.responses.Rates
import com.dgkatoudis.calculator.domain.model.DomainCurrencyWithRate
import com.dgkatoudis.calculator.domain.model.DomainRates

class RemoteToDomainRatesMapper {
    fun map(rates: Rates): DomainRates {
        return DomainRates(
            baseCurrency = rates.base,
            rates = rates.rates.map {
                DomainCurrencyWithRate(it.key, it.value)
            }
        )
    }
}