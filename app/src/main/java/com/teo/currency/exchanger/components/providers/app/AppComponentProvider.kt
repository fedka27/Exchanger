package com.teo.currency.exchanger.components.providers.app

import android.app.Application
import com.teo.currency.exchanger.components.app.ApiModule
import com.teo.currency.exchanger.components.app.AppComponent
import com.teo.currency.exchanger.components.app.AppModule
import com.teo.currency.exchanger.components.app.DaggerAppComponent
import com.teo.currency.exchanger.components.app.DatabaseModule

object AppComponentProvider {
    lateinit var appComponent: AppComponent

    fun init(application: Application) {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .apiModule(ApiModule())
            .databaseModule(DatabaseModule())
            .build()
    }
}