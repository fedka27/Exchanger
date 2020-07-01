package com.teo.currency.exchanger.business

import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.data.database.dao.CurrencyDao
import com.teo.currency.exchanger.data.database.entity.CurrencyEntity
import com.teo.currency.exchanger.data.network.ExchangerApi
import io.reactivex.rxjava3.core.Single
import java.util.*

class MainInteractorImpl(
    private val exchangerApi: ExchangerApi,
    private val currencyDao: CurrencyDao
) : MainInteractor {

    override fun getExchangerCurrency(): Single<Map<String, CurrencyExchange>> {
        return exchangerApi.getLatestCurrency()
            .map { response ->

                val base = response.base
                val rates = response.rates

                val baseValue =
                    response.rates[base] ?: 1.toDouble() //default currency relative to yourself
                val currency = getCurrency(base)
                val currentCurrency = CurrencyExchange(
                    name = currency.name(),
                    symbol = currency.symbol(),
                    rate = baseValue,
                    amount = getBalanceOfCurrency(base),
                    amountAtRate = 0.0
                )

                val hashmap = hashMapOf<String, CurrencyExchange>()
                    .apply {
                        //Add base currency as first
                        put(base, currentCurrency)
                        //Map other rates
                        putAll(
                            rates.mapValues {
                                val currencyItem = getCurrency(it.key)
                                CurrencyExchange(
                                    name = currencyItem.name(),
                                    symbol = currencyItem.symbol(),
                                    rate = it.value,
                                    amount = getBalanceOfCurrency(it.key),
                                    amountAtRate = 0.0
                                )
                            }
                        )
                    }

                hashmap.values.forEach {
                    currencyDao.insertOrUpdateCurrency(
                        CurrencyEntity(
                            name = it.name,
                            symbol = it.symbol,
                            amount = it.amount,
                            rate = it.rate
                        )
                    )
                }

                return@map hashmap.filterKeys { it.canDisplayCurrency() }
            }

    }

    //todo for test only
    private fun String.canDisplayCurrency() = this == "EUR" ||
            this == "USD" ||
            this == "RUB" ||
            this == "GBP"

    override fun getBalanceCurrencyList(): Single<List<CurrencyExchange>> {
        return Single.create { emitter ->
            val entities = currencyDao.getLatestCurrency()

            emitter.onSuccess(entities.map {
                CurrencyExchange(
                    name = it.name,
                    symbol = it.symbol,
                    rate = it.rate,
                    amount = it.amount,
                    amountAtRate = 0.0
                )
            }.filter { it.name.canDisplayCurrency() })
        }
    }

    override fun exchangeCurrency(
        currencyFrom: CurrencyExchange,
        currencyTo: CurrencyExchange
    ): Single<Pair<CurrencyExchange, CurrencyExchange>> {
        return Single.create { singleEmitter ->
            val rate = currencyFrom.calculateRateOneUnit(currencyTo)
            val amountFromAtRate = currencyFrom.amountAtRate
            val amountToAtRate = currencyFrom.calculateCurrencyByRate(rate)

            currencyDao.exchangeCurrency(
                currencyFrom.name,
                currencyTo.name,
                amountFromAtRate,
                amountToAtRate
            )

            val updatedFrom = currencyDao.getCurrency(currencyFrom.name)!!
                .let {
                    CurrencyExchange(
                        name = it.name,
                        symbol = it.symbol,
                        rate = it.rate,
                        amount = it.amount,
                        amountAtRate = 0.0
                    )
                }

            val updatedTo = currencyDao.getCurrency(currencyTo.name)!!
                .let {
                    CurrencyExchange(
                        name = it.name,
                        symbol = it.symbol,
                        rate = it.rate,
                        amount = it.amount,
                        amountAtRate = 0.0
                    )
                }

            //Return result
            singleEmitter.onSuccess(
                Pair(
                    updatedFrom,
                    updatedTo
                )
            )
        }
    }

    private inline fun Currency.name() = this.currencyCode
    private inline fun Currency.symbol() = this.symbol

    private fun getBalanceOfCurrency(currency: String): Double {
        return currencyDao.getCurrency(currency)?.amount ?: 100.00.toDouble()
    }

    private fun getCurrency(currency: String) = Currency.getInstance(currency)
}