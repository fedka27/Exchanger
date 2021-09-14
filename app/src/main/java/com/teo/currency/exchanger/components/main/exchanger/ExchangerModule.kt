package com.teo.currency.exchanger.components.main.exchanger

import com.teo.currency.exchanger.business.ExchangerInteractor
import com.teo.currency.exchanger.business.ExchangerInteractorImpl
import com.teo.currency.exchanger.data.database.AppDatabase
import com.teo.currency.exchanger.data.network.ExchangerApi
import com.teo.currency.exchanger.presentation.main.exchanger.ExchangerContract
import com.teo.currency.exchanger.presentation.main.exchanger.ExchangerPresenter
import dagger.Module
import dagger.Provides

@Module
class ExchangerModule {

    @ExchangerScope
    @Provides
    fun providePresenter(exchangerInteractor: ExchangerInteractor): ExchangerContract.Presenter {
        return ExchangerPresenter(exchangerInteractor)
    }


    @ExchangerScope
    @Provides
    fun provideExchangerInteractor(
        exchangerApi: ExchangerApi, //todo to repo
        appDatabase: AppDatabase
    ): ExchangerInteractor {
        return ExchangerInteractorImpl(exchangerApi, appDatabase.currencyDao())
    }

}