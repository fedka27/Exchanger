package com.teo.currency.exchanger

import android.app.Application
import com.teo.currency.exchanger.components.providers.app.AppComponentProvider

class ExchangerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AppComponentProvider.init(this)
    }
}