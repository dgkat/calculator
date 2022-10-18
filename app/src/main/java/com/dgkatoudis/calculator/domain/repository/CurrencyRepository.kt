package com.dgkatoudis.calculator.domain.repository

import com.dgkatoudis.calculator.domain.model.DomainRates
import com.dgkatoudis.calculator.domain.model.Resource

interface CurrencyRepository {
    suspend fun getRatesByCurrency(
        base: String
    ): Resource<DomainRates>
}