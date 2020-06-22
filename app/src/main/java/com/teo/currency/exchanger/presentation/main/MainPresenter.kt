package com.teo.currency.exchanger.presentation.main

import android.util.Log
import com.teo.currency.exchanger.data.network.ExchangerApi
import com.teo.currency.exchanger.presentation.base.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainPresenter(
    private val exchangerApi: ExchangerApi
) : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun onStart() {
        loadExchangerCurrency()
    }

    private fun loadExchangerCurrency() {
//        compositeDisposable.add(
//            exchangerApi.getLatestCurrency()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeBy(
//                    onError = {
//                        it.printStackTrace()
//                    },
//                    onSuccess = { latestCurrencyResponse ->
//                        Log.d(TAG, "loadExchangerCurreny: ${latestCurrencyResponse}")
//                    }
//                )
//        )
    }
}