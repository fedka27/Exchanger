package com.teo.currency.exchanger.components.api

import com.teo.currency.exchanger.components.app.AppComponent
import com.teo.currency.exchanger.data.network.ExchangerApi
import dagger.Component

@ApiScope
@Component(
    modules = [
        ApiModule::class
    ],
    dependencies = [
        AppComponent::class
    ]
)
interface ApiComponent {

    fun provideExchangerApi(): ExchangerApi

    @Component.Builder
    interface Builder {

        fun module(apiModule: ApiModule): Builder

        fun dependency(appComponent: AppComponent): Builder

        fun build(): ApiComponent

    }
}