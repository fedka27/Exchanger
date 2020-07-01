package com.teo.currency.exchanger.presentation.main

import android.util.Log
import com.teo.currency.exchanger.business.MainInteractor
import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.data.database.dao.Errors
import com.teo.currency.exchanger.presentation.base.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainPresenter(
    private val mainInteractor: MainInteractor
) : BasePresenter<MainContract.View>(),
    MainContract.Presenter {
    companion object {
        private const val INTERVAL_CURRENCY_UPDATE = 30L //Seconds
    }

    private val currencySubject = PublishSubject.create<Map<String, CurrencyExchange>>()

    /**
     * first - from
     * second - to
     * */
    private var currencyFrom: CurrencyExchange? = null
    private var currencyTo: CurrencyExchange? = null

    override fun onStart() {
        super<BasePresenter>.onStart()

        initCurrencyUpdater()

        startTimer()
    }

    private fun initCurrencyUpdater() {
        compositeDisposable.add(
            //Wait event from subject
            currencySubject
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    view.hideContent()
                    view.showProgress()
                }
                .doOnNext {
                    view.displayContent()
                    view.hideProgress()
                }
                .subscribeBy(
                    onError = {
                        it.printStackTrace()
                        view.showErrorLoad()
                    },
                    onNext = { map ->
                        val list = map.values.toList()

                        if (list.isEmpty()) {
                            view.showErrorLoad()
                            return@subscribeBy
                        }

                        //todo refactor
                        currencyFrom = if (currencyFrom == null) {
                            list.first()
                        } else {
                            list.getActualCurrency(currencyFrom!!)
                        }

                        currencyTo = if (currencyTo == null) {
                            val defCurrency = list.first()
                            defCurrency
                        } else {
                            list.getActualCurrency(currencyTo!!)
                        }

                        view.setCurrencyList(list)

                        //Static currency without change amount
                        updatedCurrencyTo(currencyTo!!)

                        Log.d(TAG, "loadExchangerCurrency: ${map.size}")
                    }
                )
        )
    }

    private fun startTimer() {
        compositeDisposable.add(
            //Load immediately and updates by timer
            Observable.interval(0, INTERVAL_CURRENCY_UPDATE, TimeUnit.SECONDS)
                //load actual currency, starting on subscribe
                .flatMapSingle { mainInteractor.getExchangerCurrency() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        it.printStackTrace()
                    },
                    onNext = {
                        //Invoke to subject
                        currencySubject.onNext(it)
                    }
                )
        )
    }

    private fun List<CurrencyExchange>.getActualCurrency(currency: CurrencyExchange): CurrencyExchange {
        return find { it == currency }!!
            .copy(amountAtRate = currency.amountAtRate)
    }

    override fun onExchangeClick(from: CurrencyExchange, to: CurrencyExchange) {
        compositeDisposable.add(
            mainInteractor.exchangeCurrency(from, to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { currency ->
                    this.currencyFrom = currency.first
                    this.currencyTo = currency.second

                    view.updateExchangeCurrency(currencyFrom!!, currencyTo!!)
                }
                .observeOn(Schedulers.io())
                // load actual balance
                .flatMap { mainInteractor.getBalanceCurrencyList() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        it.printStackTrace()
                        when (it) {
                            is Errors.ExceptionZeroAmountValue -> {
                                view.showErrorAmountZero()
                            }
                            is Errors.ExceptionNotEnoughAmount -> {
                                view.showErrorNotEnoughAmount()
                            }
                        }
                    },
                    onSuccess = { list ->
                        view.showAllCurrencyAmount(list)
                    })
        )
    }

    override fun changeAmountFrom(from: CurrencyExchange) {
        currencyFrom = from

        val to = currencyTo!!
        val rate = from.calculateRateOneUnit(to)

        to.amountAtRate = from.calculateCurrencyByRate(rate)

        Log.d(
            TAG,
            "changeAmountFrom: ${from.amountAtRate}: ${from.name} -> ${to.name} = ${to.amountAtRate}"
        )
        view.updatedCurrencyToItem(to)
    }

    override fun changeAmountTo(to: CurrencyExchange) {
        currencyTo = to

        val from = currencyFrom!!

        val rate = to.calculateRateOneUnit(from)
        from.amountAtRate = to.calculateCurrencyByRate(rate)

        Log.d(
            TAG,
            "changeAmountFrom: ${to.amountAtRate}: ${from.name} -> ${to.name} = ${to.amountAtRate}"
        )
        view.updatedCurrencyFromItem(from)
    }

    override fun updatedCurrencyFrom(currency: CurrencyExchange) {
        currencyFrom = currency

        view.updateCurrencyForEnd(currencyFrom!!, currencyTo!!)

        changeAmountTo(currencyTo!!)
    }

    override fun updatedCurrencyTo(currency: CurrencyExchange) {
        currencyTo = currency

        view.updateCurrencyForStart(currencyTo!!, currencyFrom!!)

        changeAmountFrom(currencyFrom!!)
    }
}