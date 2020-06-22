package com.teo.currency.exchanger.presentation.main

import android.util.Log
import com.teo.currency.exchanger.business.MainInteractor
import com.teo.currency.exchanger.business.dto.Currency
import com.teo.currency.exchanger.presentation.base.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MainPresenter(
    private val mainInteractor: MainInteractor
) : BasePresenter<MainContract.View>(), MainContract.Presenter {
    companion object {
        private const val INTERVAL_CURRENCY_UPDATE = 30L //Seconds
    }

    private val currencySubject = PublishSubject.create<Pair<Currency, Map<String, Currency>>>()

    override fun onCreate() {
        super<BasePresenter>.onCreate()

        initCurrencyUpdater()
    }

    override fun onStart() {
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
                .subscribeBy(
                    onError = {
                        it.printStackTrace()
                    },
                    onNext = { pair ->
                        val current = pair.first
                        val map = pair.second

                        view.updateAdapter(current, map.values)

                        Log.d(TAG, "current: ${current.name}")
                        Log.d(TAG, "loadExchangerCurrency: ${map.size}")
                    }
                )
        )
    }
}