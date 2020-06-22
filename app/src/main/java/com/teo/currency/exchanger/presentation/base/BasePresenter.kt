package com.teo.currency.exchanger.presentation.base

import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BasePresenter<VIEW : BaseContractView> : BaseContractPresenter<VIEW> {
    val TAG: String = BasePresenter::class.java.simpleName

    override lateinit var view: VIEW

    protected var compositeDisposable = CompositeDisposable()

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}