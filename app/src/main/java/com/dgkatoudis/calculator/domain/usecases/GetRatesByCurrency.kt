package com.dgkatoudis.calculator.domain.usecases

import com.dgkatoudis.calculator.domain.model.DomainRates
import com.dgkatoudis.calculator.domain.model.Resource
import com.dgkatoudis.calculator.domain.repository.CurrencyRepository

class GetRatesByCurrency(
    private val repository: CurrencyRepository
) {
    suspend operator fun invoke(base: String): Resource<DomainRates> {
        return repository.getRatesByCurrency(base = base)
    }
}