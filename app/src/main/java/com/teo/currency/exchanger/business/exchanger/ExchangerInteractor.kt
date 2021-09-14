package com.teo.currency.exchanger.business.exchanger

import com.teo.currency.exchanger.business.exchanger.model.CurrencyExchange
import io.reactivex.rxjava3.core.Single

interface ExchangerInteractor {

    fun getExchangerCurrency(): Single<Map<String, CurrencyExchange>>

    fun exchangeCurrency(
        currencyFrom: CurrencyExchange,
        currencyTo: CurrencyExchange
    ): Single<Pair<CurrencyExchange, CurrencyExchange>>

    fun getCurrencyList(): Single<List<CurrencyExchange>>
}