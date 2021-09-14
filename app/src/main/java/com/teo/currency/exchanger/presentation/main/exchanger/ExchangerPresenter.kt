package com.teo.currency.exchanger.presentation.main.exchanger

import android.util.Log
import com.teo.currency.exchanger.business.ExchangerInteractor
import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.data.database.dao.Errors
import com.teo.currency.exchanger.presentation.base.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import moxy.InjectViewState
import java.util.concurrent.TimeUnit

@InjectViewState
class ExchangerPresenter(
    private val exchangerInteractor: ExchangerInteractor
) : BasePresenter<ExchangerView>() {
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


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        initCurrencyUpdater()
        startTimer()
    }

    private fun startTimer() {
        compositeDisposable.add(Observable
            .interval(0, INTERVAL_CURRENCY_UPDATE, TimeUnit.SECONDS)
            .flatMapSingle { exchangerInteractor.getExchangerCurrency() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                currencySubject.onNext(it)
            }, {
                it.printStackTrace()
                viewState.showErrorLoad()
            })
        )
    }

    private fun initCurrencyUpdater() {
        compositeDisposable.add(currencySubject
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.hideContent()
                viewState.showProgress()
            }
            .doOnNext {
                viewState.displayContent()
                viewState.hideProgress()
            }
            .subscribe(this::handleCurrencyUpdated) {
                it.printStackTrace()
                viewState.showErrorLoad()
            }
        )
    }

    private fun handleCurrencyUpdated(map: Map<String, CurrencyExchange>) {
        val list = map.values.toList()

        if (list.isEmpty()) {
            viewState.showErrorLoad()
            return
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

        viewState.setCurrencyList(list)

        //Static currency without change amount
        updatedCurrencyTo(currencyTo!!)

        Log.d(TAG, "loadExchangerCurrency: ${map.size}")

    }

    private fun List<CurrencyExchange>.getActualCurrency(currency: CurrencyExchange): CurrencyExchange {
        return find { it == currency }!!
            .copy(amountAtRate = currency.amountAtRate)
    }

    fun onExchangeClick(
        from: CurrencyExchange,
        to: CurrencyExchange
    ) {

        compositeDisposable.add(
            exchangerInteractor.exchangeCurrency(from, to)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { currency ->
                    this.currencyFrom = currency.first
                    this.currencyTo = currency.second

                    viewState.updateExchangeCurrency(currencyFrom!!, currencyTo!!)
                }
                .observeOn(Schedulers.io())
                .flatMap { exchangerInteractor.getCurrencyList() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onError = {
                        it.printStackTrace()
                        when (it) {
                            is Errors.ExceptionZeroAmountValue -> {
                                viewState.showErrorAmountZero()
                            }
                            is Errors.ExceptionNotEnoughAmount -> {
                                viewState.showErrorNotEnoughAmount()
                            }
                        }
                    },
                    onSuccess = { list ->
                        viewState.showAllCurrencyAmount(list)
                    })
        )
    }

    fun changeAmountFrom(from: CurrencyExchange) {
        currencyFrom = from

        val to = currencyTo!!
        val rate = from.calculateRateOneUnit(to)

        to.amountAtRate = from.calculateCurrencyByRate(rate)

        Log.d(
            TAG,
            "changeAmountFrom: ${from.amountAtRate}: ${from.name} -> ${to.name} = ${to.amountAtRate}"
        )
        viewState.updatedCurrencyToItem(to)
    }

    fun changeAmountTo(to: CurrencyExchange) {
        currencyTo = to

        val from = currencyFrom!!

        val rate = to.calculateRateOneUnit(from)
        from.amountAtRate = to.calculateCurrencyByRate(rate)

        Log.d(
            TAG,
            "changeAmountFrom: ${to.amountAtRate}: ${from.name} -> ${to.name} = ${to.amountAtRate}"
        )
        viewState.updatedCurrencyFromItem(from)
    }

    fun updatedCurrencyFrom(currency: CurrencyExchange) {
        currencyFrom = currency

        viewState.updateCurrencyForEnd(currencyFrom!!, currencyTo!!)

        changeAmountTo(currencyTo!!)
    }

    fun updatedCurrencyTo(currency: CurrencyExchange) {
        currencyTo = currency

        viewState.updateCurrencyForStart(currencyTo!!, currencyFrom!!)

        changeAmountFrom(currencyFrom!!)
    }
}