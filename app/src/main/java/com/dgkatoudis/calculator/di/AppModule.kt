package com.dgkatoudis.calculator.di

import com.dgkatoudis.calculator.data.mappers.RemoteToDomainRatesMapper
import com.dgkatoudis.calculator.data.remote.CurrencyApi
import com.dgkatoudis.calculator.data.repository.CurrencyRepositoryImpl
import com.dgkatoudis.calculator.domain.repository.CurrencyRepository
import com.dgkatoudis.calculator.domain.usecases.CalculateResult
import com.dgkatoudis.calculator.domain.usecases.GetRatesByCurrency
import com.dgkatoudis.calculator.presentation.mappers.DomainToUiCurrencyWithRateMapper
import com.dgkatoudis.calculator.presentation.mappers.DomainToUiRatesMapper
import com.dgkatoudis.calculator.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCalculateResult(): CalculateResult {
        return CalculateResult()
    }

    @Provides
    fun provideGetRatesByCurrency(repository: CurrencyRepository): GetRatesByCurrency {
        return GetRatesByCurrency(repository = repository)
    }

    @Singleton
    @Provides
    fun provideCurrencyApi(): CurrencyApi {
        return Retrofit
            .Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideCurrencyRepository(
        api: CurrencyApi,
        remoteToDomainRatesMapper: RemoteToDomainRatesMapper
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(
            api = api,
            remoteToDomainRatesMapper = remoteToDomainRatesMapper
        )
    }

    @Provides
    fun provideRemoteToDomainRatesMapper(): RemoteToDomainRatesMapper {
        return RemoteToDomainRatesMapper()
    }

    @Provides
    fun provideDomainToUiCurrencyWithRateMapper(): DomainToUiCurrencyWithRateMapper {
        return DomainToUiCurrencyWithRateMapper()
    }

    @Provides
    fun provideDomainToUiRatesMapper(
        domainToUiCurrencyWithRateMapper: DomainToUiCurrencyWithRateMapper
    ): DomainToUiRatesMapper {
        return DomainToUiRatesMapper(domainToUiCurrencyWithRateMapper)
    }
}