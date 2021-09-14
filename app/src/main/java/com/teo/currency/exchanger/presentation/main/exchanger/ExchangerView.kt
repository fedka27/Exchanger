package com.teo.currency.exchanger.presentation.main.exchanger

import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ExchangerView : BaseView{
        fun showProgress()

        fun hideProgress()

        fun displayContent()

        fun hideContent()

        fun setCurrencyList(values: List<CurrencyExchange>)

        fun updateExchangeCurrency(currencyFrom: CurrencyExchange, currencyTo: CurrencyExchange)

        fun updateCurrencyForEnd(currency: CurrencyExchange, item: CurrencyExchange)

        fun updateCurrencyForStart(currency: CurrencyExchange, item: CurrencyExchange)

        fun updatedCurrencyFromItem(currency: CurrencyExchange)

        fun updatedCurrencyToItem(currency: CurrencyExchange)

        fun showAllCurrencyAmount(list: List<CurrencyExchange>)

        fun showErrorLoad()

        fun showErrorAmountZero()

        fun showErrorNotEnoughAmount()

}