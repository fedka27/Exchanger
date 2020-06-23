package com.teo.currency.exchanger.data.network

import com.teo.currency.exchanger.data.network.responses.LatestCurrencyResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ExchangerApi {
    companion object {
        const val BASE_URL = "https://api.exchangeratesapi.io/"
    }

    @GET("latest")
    fun getLatestCurrency(): Single<LatestCurrencyResponse>
}