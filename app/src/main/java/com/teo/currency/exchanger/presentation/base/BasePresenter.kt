package com.teo.currency.exchanger.presentation.base

import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.MvpPresenter

open class BasePresenter<VIEW : BaseView> : MvpPresenter<VIEW>() {
    val TAG: String = BasePresenter::class.java.simpleName

    protected var compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        compositeDisposable.clear()
    }
}