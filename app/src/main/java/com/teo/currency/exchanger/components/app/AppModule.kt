package com.teo.currency.exchanger.components.app

import android.content.Context
import com.teo.currency.exchanger.utils.AppResourceProvider
import com.teo.currency.exchanger.utils.impl.AppResourceProviderImpl
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val applicationContext: Context) {

    @Provides
    @AppScope
    fun provideApplicationContext() = applicationContext

    @Provides
    @AppScope
    fun provideResourceProvider(context: Context): AppResourceProvider =
        AppResourceProviderImpl(context)

}