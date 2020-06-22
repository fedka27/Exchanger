package com.teo.currency.exchanger.presentation.base

interface BaseContractPresenter<VIEW : BaseContractView> {
    var view: VIEW

    fun onCreate(){}

    fun onStart(){}

    fun onStop(){}

    fun onDestroy(){}
}