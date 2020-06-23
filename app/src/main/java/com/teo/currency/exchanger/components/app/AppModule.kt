package com.teo.currency.exchanger.components.app

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val applicationContext: Context) {

    @Provides
    @AppScope
    fun provideApplicationContext() = applicationContext

}