package com.teo.currency.exchanger.components.providers.main

import com.teo.currency.exchanger.components.main.DaggerMainComponent
import com.teo.currency.exchanger.components.main.MainComponent
import com.teo.currency.exchanger.components.main.MainModule
import com.teo.currency.exchanger.components.providers.app.AppComponentProvider

object MainComponentProvider {
    private var mainComponent: MainComponent? = null

    fun get(): MainComponent {
        if (mainComponent == null) {
            mainComponent = DaggerMainComponent.builder()
                .module(MainModule())
                .dependencyApp(AppComponentProvider.appComponent)
                .build()
        }

        return mainComponent!!
    }
}