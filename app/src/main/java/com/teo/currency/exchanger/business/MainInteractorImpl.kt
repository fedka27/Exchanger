package com.teo.currency.exchanger.business

import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.data.database.dao.CurrencyDao
import com.teo.currency.exchanger.data.database.entity.CurrencyEntity
import com.teo.currency.exchanger.data.network.ExchangerApi
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiConsumer

class MainInteractorImpl(
    private val exchangerApi: ExchangerApi,
    private val currencyDao: CurrencyDao
) : MainInteractor {

    override fun getExchangerCurrency(): Single<Map<String, CurrencyExchange>> {
        return exchangerApi.getLatestCurrency()
            .map { response ->

                val base = response.base
                val rates = response.rates

                val baseValue = response.rates[base] ?: 1.0 //default currency relative to yourself
                val currency = getCurrency(base)
                val currentCurrency = CurrencyExchange(
                    currency,
                    baseValue,
                    getBalanceOfCurrency(base)
                )

                val hashmap = hashMapOf<String, CurrencyExchange>()
                    .apply {
                        //Add base currency as first
                        put(base, currentCurrency)
                        //Map other rates
                        putAll(
                            rates.mapValues {
                                CurrencyExchange(
                                    getCurrency(it.key),
                                    it.value,
                                    getBalanceOfCurrency(it.key)
                                )
                            }
                        )
                    }

                hashmap.values.forEach {
                    currencyDao.insertCurrency(
                        CurrencyEntity(
                            it.currency.currencyCode,
                            it.amount,
                            it.value
                        )
                    )
                }

                //todo for test only
                return@map hashmap.filterKeys {
                    it == "EUR" ||
                            it == "USD" ||
                            it == "RUB" ||
                            it == "GBR"
                }
            }

    }

    private fun getBalanceOfCurrency(currency: String): Double {
        //todo get balance of current
        return currencyDao.getCurrency(currency)?.amount ?: 100.00
    }

    private fun getCurrency(currency: String) = java.util.Currency.getInstance(currency)
}