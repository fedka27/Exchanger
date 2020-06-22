package com.teo.currency.exchanger.components.providers.app

import android.app.Application
import com.teo.currency.exchanger.components.api.ApiComponent
import com.teo.currency.exchanger.components.api.ApiModule
import com.teo.currency.exchanger.components.api.DaggerApiComponent
import com.teo.currency.exchanger.components.app.AppComponent
import com.teo.currency.exchanger.components.app.AppModule
import com.teo.currency.exchanger.components.app.DaggerAppComponent

object AppComponentProvider {
    lateinit var appComponent: AppComponent
    lateinit var apiComponent: ApiComponent

    fun init(application: Application) {
        appComponent = DaggerAppComponent.builder()
            .module(AppModule(application))
            .build()

        apiComponent = DaggerApiComponent.builder()
            .module(ApiModule())
            .dependency(appComponent)
            .build()
    }
}