package com.teo.currency.exchanger.components.api

import android.content.Context
import com.teo.currency.exchanger.components.app.AppScope
import com.teo.currency.exchanger.data.network.ExchangerApi
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private var exchangerApi: ExchangerApi? = null

    private val okHttpClient = OkHttpClient
        .Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @ApiScope
    @Provides
    fun provideExchangerApi(): ExchangerApi {
        if (exchangerApi == null) {
            exchangerApi = Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ExchangerApi.BASE_URL)
                .client(okHttpClient)
                .build()
                .create(ExchangerApi::class.java)
        }
        return exchangerApi!!
    }


}