package com.dgkatoudis.calculator.data.remote

import com.dgkatoudis.calculator.data.responses.Rates
import com.dgkatoudis.calculator.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("latest")
    suspend fun getRatesByCurrency(
        @Query("base") base: String,
        @Query("apikey") apikey: String = API_KEY
    ): Rates
}