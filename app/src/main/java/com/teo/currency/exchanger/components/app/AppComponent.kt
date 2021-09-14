package com.teo.currency.exchanger.components.app

import com.teo.currency.exchanger.data.database.AppDatabase
import com.teo.currency.exchanger.data.database.dao.CurrencyDao
import com.teo.currency.exchanger.data.database.dao.MoviesDao
import com.teo.currency.exchanger.data.network.exchanger.ExchangerApi
import dagger.Component

@AppScope
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {

    fun provideExchangerApi(): ExchangerApi

    fun provideDatabase(): AppDatabase

    fun provideCurrencyDao(): CurrencyDao
    fun provideMoviesDao(): MoviesDao

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule): Builder

        fun apiModule(apiModule: ApiModule): Builder

        fun databaseModule(databaseModule: DatabaseModule): Builder

        fun build(): AppComponent
    }
}