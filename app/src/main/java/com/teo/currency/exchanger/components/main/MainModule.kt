package com.teo.currency.exchanger.components.main

import com.teo.currency.exchanger.presentation.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainPresenter(): MainPresenter {
        return MainPresenter()
    }
}