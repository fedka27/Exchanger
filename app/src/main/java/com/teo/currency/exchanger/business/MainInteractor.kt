package com.teo.currency.exchanger.business

import com.teo.currency.exchanger.business.dto.Currency
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.internal.operators.observable.ObservableCombineLatest

interface MainInteractor {

    fun getExchangerCurrency(): Single<Pair<Currency, Map<String, Currency>>>
}