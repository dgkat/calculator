package com.dgkatoudis.calculator.di

import com.dgkatoudis.calculator.domain.usecases.CalculateResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCalculateResult(): CalculateResult {
        return CalculateResult()
    }
}