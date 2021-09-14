package com.teo.currency.exchanger.components.providers.main.exchanger

import com.teo.currency.exchanger.components.main.exchanger.DaggerExchangerComponent
import com.teo.currency.exchanger.components.main.exchanger.ExchangerComponent
import com.teo.currency.exchanger.components.main.exchanger.ExchangerModule
import com.teo.currency.exchanger.components.providers.app.AppComponentProvider

object ExchangerComponentProvider {
    val exchangerComponent: ExchangerComponent by lazy {
        DaggerExchangerComponent.builder()
            .module(ExchangerModule())
            .dependencyApp(AppComponentProvider.appComponent)
            .build()
    }
}