package com.teo.currency.exchanger.components.providers.main

import com.teo.currency.exchanger.components.main.DaggerMainComponent
import com.teo.currency.exchanger.components.main.MainComponent
import com.teo.currency.exchanger.components.main.MainModule
import com.teo.currency.exchanger.components.main.exchanger.ExchangerComponent
import com.teo.currency.exchanger.components.providers.app.AppComponentProvider

object MainComponentProvider {
    val mainComponent: MainComponent by lazy {
        DaggerMainComponent.builder()
            .module(MainModule())
            .dependencyApp(AppComponentProvider.appComponent)
            .build()
    }
}