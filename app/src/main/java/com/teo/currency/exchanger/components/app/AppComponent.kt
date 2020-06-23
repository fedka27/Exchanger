package com.teo.currency.exchanger.components.app

import com.teo.currency.exchanger.data.database.AppDatabase
import com.teo.currency.exchanger.data.network.ExchangerApi
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

    @Component.Builder
    interface Builder {
        fun appModule(appModule: AppModule): Builder

        fun apiModule(apiModule: ApiModule): Builder

        fun databaseModule(databaseModule: DatabaseModule): Builder

        fun build(): AppComponent
    }
}