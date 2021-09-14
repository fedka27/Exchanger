package com.teo.currency.exchanger.components.app

import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.data.network.exchanger.ExchangerApi
import com.teo.currency.exchanger.data.network.movies.MoviesApi
import com.teo.currency.exchanger.utils.AppResourceProvider
import com.teo.currency.exchanger.utils.impl.AppResourceProviderImpl
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @AppScope
    @Provides
    fun provideOkhttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @AppScope
    @Provides
    fun provideGsonConverterFactory(): Converter.Factory = GsonConverterFactory.create()

    @AppScope
    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory = RxJava3CallAdapterFactory.create()

    @AppScope
    @Provides
    fun provideExchangerApi(
        resourceProvider: AppResourceProvider,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): ExchangerApi {
        return Retrofit.Builder()
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .baseUrl(resourceProvider.getString(R.string.base_url_exchanger))
            .client(okHttpClient)
            .build()
            .create(ExchangerApi::class.java)
    }

    @AppScope
    @Provides
    fun provideMoviesApi(
        resourceProvider: AppResourceProvider,
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        callAdapterFactory: CallAdapter.Factory
    ): MoviesApi {
        return Retrofit.Builder()
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(converterFactory)
            .baseUrl(resourceProvider.getString(R.string.base_url_movies))
            .client(okHttpClient)
            .build()
            .create(MoviesApi::class.java)
    }


}