package com.teo.currency.exchanger.components.providers.app

import android.app.Application
import com.teo.currency.exchanger.components.app.*

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