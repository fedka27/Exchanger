package com.teo.currency.exchanger.components.main

import com.teo.currency.exchanger.business.ExchangerInteractor
import com.teo.currency.exchanger.business.ExchangerInteractorImpl
import com.teo.currency.exchanger.data.database.AppDatabase
import com.teo.currency.exchanger.data.network.ExchangerApi
import com.teo.currency.exchanger.presentation.main.MainContract
import com.teo.currency.exchanger.presentation.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainPresenter(): MainContract.Presenter {
        return MainPresenter()
    }
}