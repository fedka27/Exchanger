package com.teo.currency.exchanger.presentation.main

import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.presentation.base.BaseContractPresenter
import com.teo.currency.exchanger.presentation.base.BaseContractView

object MainContract {
    interface View : BaseContractView {

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

    interface Presenter : BaseContractPresenter<View> {
        fun onExchangeClick(
            from: CurrencyExchange,
            to: CurrencyExchange
        )

        fun changeAmountFrom(from: CurrencyExchange)

        fun changeAmountTo(to: CurrencyExchange)

        fun updatedCurrencyFrom(currency: CurrencyExchange)

        fun updatedCurrencyTo(currency: CurrencyExchange)

    }
}