package com.teo.currency.exchanger.business

import com.teo.currency.exchanger.business.dto.Currency
import com.teo.currency.exchanger.data.network.ExchangerApi
import io.reactivex.rxjava3.core.Single

class MainInteractorImpl(
    private val exchangerApi: ExchangerApi
) : MainInteractor {

    override fun getExchangerCurrency(): Single<Pair<Currency, Map<String, Currency>>> {
        return exchangerApi.getLatestCurrency()
            .map { response ->

                val base = response.base
                val rates = response.rates

                val baseValue = response.rates[base] ?: 1.0 //default currency relative to yourself
                val currentCurrency = Currency(
                    base,
                    baseValue,
                    getBalanceOfCurrency(base),
                    getSymbol(base)
                )

                val map = rates.mapValues {
                    val currency = it.key
                    Currency(it.key, it.value, getBalanceOfCurrency(currency), getSymbol(currency))
                }

                return@map Pair(currentCurrency, map)
            }
    }

    private fun getSymbol(currency: String) = java.util.Currency.getInstance(currency).symbol

    private fun getBalanceOfCurrency(currency: String): Double {
        //todo get balance of current
        return 100.00
    }
}