package com.teo.currency.exchanger.presentation.main

import android.util.Log
import com.teo.currency.exchanger.business.MainInteractor
import com.teo.currency.exchanger.business.dto.CurrencyExchange
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

    private fun startTimer() {
        compositeDisposable.add(
            Observable.interval(0, INTERVAL_CURRENCY_UPDATE, TimeUnit.SECONDS)
                .flatMapSingle { mainInteractor.getExchangerCurrency() }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        it.printStackTrace()
                    },
                    onNext = {
                        currencySubject.onNext(it)
                    }
                )
        )
    }

    private fun initCurrencyUpdater() {
        compositeDisposable.add(
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
                    },
                    onNext = { map ->
                        val list = map.values

                        if (currencyFrom == null){
                            val defCurrency = list.first()

                            currencyFrom = defCurrency
                        }
                        if (currencyTo == null){
                            val defCurrency = list.first()

                            currencyTo = defCurrency
                        }

                        view.setCurrencyList(list)

                        Log.d(TAG, "loadExchangerCurrency: ${map.size}")
                    }
                )
        )
    }

    override fun onExchangeClick(
        currencyFrom: CurrencyExchange,
        currencyTo: CurrencyExchange
    ) {

        compositeDisposable.add(
            mainInteractor
                .exchangeCurrency(currencyFrom, currencyTo, currencyFrom.amountAtRate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        it.printStackTrace()
                        //todo error
                    },
                    onSuccess = { currency ->
                        view.updateExchangeCurrency(currency.first, currency.second)
                        view.clearExchangeFields()
                    })
        )
    }

    override fun changeAmountFrom(from: CurrencyExchange) {
        val to = currencyTo!!
        val rate = from.calculateRateOneUnit(to)

        to.amountAtRate = from.calculateCurrencyByRate(rate)

        Log.d(TAG, "changeAmountFrom: ${from.amountAtRate}: ${from.name} -> ${to.name} = ${to.amountAtRate}")
        view.updatedCurrencyToItem(to)
    }

    override fun changeAmountTo(to: CurrencyExchange) {
        val from = currencyFrom!!

        val rate = to.calculateRateOneUnit(from)
        from.amountAtRate = to.calculateCurrencyByRate(rate)

        Log.d(TAG, "changeAmountFrom: ${to.amountAtRate}: ${from.name} -> ${to.name} = ${to.amountAtRate}")
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