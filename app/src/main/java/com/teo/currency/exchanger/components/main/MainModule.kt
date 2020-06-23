package com.teo.currency.exchanger.components.main

import com.teo.currency.exchanger.business.MainInteractor
import com.teo.currency.exchanger.business.MainInteractorImpl
import com.teo.currency.exchanger.data.database.AppDatabase
import com.teo.currency.exchanger.data.database.dao.CurrencyDao
import com.teo.currency.exchanger.data.network.ExchangerApi
import com.teo.currency.exchanger.presentation.main.MainContract
import com.teo.currency.exchanger.presentation.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideMainPresenter(mainInteractor: MainInteractor): MainContract.Presenter {
        return MainPresenter(mainInteractor)
    }

    @MainScope
    @Provides
    fun provideMainInteractor(
        exchangerApi: ExchangerApi,
        appDatabase: AppDatabase
    ): MainInteractor {
        return MainInteractorImpl(exchangerApi, appDatabase.currencyDao())
    }
}