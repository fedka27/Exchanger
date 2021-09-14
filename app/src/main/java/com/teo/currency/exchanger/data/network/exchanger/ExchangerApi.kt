package com.teo.currency.exchanger.data.network.exchanger

import com.teo.currency.exchanger.data.network.exchanger.responses.LatestCurrencyResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ExchangerApi {
    @GET("latest")
    fun getLatestCurrency(): Single<LatestCurrencyResponse>
}