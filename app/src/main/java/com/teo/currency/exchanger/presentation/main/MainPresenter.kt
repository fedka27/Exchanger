package com.teo.currency.exchanger.presentation.main

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
import java.util.concurrent.TimeUnit

class MainPresenter() : BasePresenter<MainContract.View>(),
    MainContract.Presenter {

}