package com.dgkatoudis.calculator.data.repository

import com.dgkatoudis.calculator.data.mappers.RemoteToDomainRatesMapper
import com.dgkatoudis.calculator.data.remote.CurrencyApi
import com.dgkatoudis.calculator.domain.model.DomainRates
import com.dgkatoudis.calculator.domain.model.Resource
import com.dgkatoudis.calculator.domain.repository.CurrencyRepository
import javax.inject.Singleton

@Singleton
class CurrencyRepositoryImpl(
    private val api: CurrencyApi,
    private val remoteToDomainRatesMapper: RemoteToDomainRatesMapper
) : CurrencyRepository {

    override suspend fun getRatesByCurrency(base: String): Resource<DomainRates> {
        val response = try {
            api.getRatesByCurrency(base)
        } catch (e: Exception) {
            return Resource.Error(message = "error")
        }
        return Resource.Success(data = remoteToDomainRatesMapper.map(response))
    }

}