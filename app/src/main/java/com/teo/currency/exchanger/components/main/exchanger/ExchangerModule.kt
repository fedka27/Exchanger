package com.teo.currency.exchanger.components.main.exchanger

import com.teo.currency.exchanger.business.exchanger.ExchangerInteractor
import com.teo.currency.exchanger.business.exchanger.ExchangerInteractorImpl
import com.teo.currency.exchanger.data.database.AppDatabase
import com.teo.currency.exchanger.data.database.dao.CurrencyDao
import com.teo.currency.exchanger.data.network.exchanger.ExchangerApi
import com.teo.currency.exchanger.presentation.main.exchanger.ExchangerPresenter
import dagger.Module
import dagger.Provides

@Module
class ExchangerModule {

    @ExchangerScope
    @Provides
    fun providePresenter(exchangerInteractor: ExchangerInteractor): ExchangerPresenter {
        return ExchangerPresenter(exchangerInteractor)
    }


    @ExchangerScope
    @Provides
    fun provideExchangerInteractor(
        exchangerApi: ExchangerApi, //todo to repo
        currencyDao: CurrencyDao
    ): ExchangerInteractor {
        return ExchangerInteractorImpl(exchangerApi, currencyDao)
    }

}