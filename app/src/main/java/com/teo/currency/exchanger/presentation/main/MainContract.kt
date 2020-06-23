package com.teo.currency.exchanger.presentation.main

import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.presentation.base.BaseContractPresenter
import com.teo.currency.exchanger.presentation.base.BaseContractView

object MainContract {
    interface View : BaseContractView{

        fun showProgress()

        fun hideProgress()

        fun displayContent()

        fun hideContent()

        fun setCurrencyList(values: Collection<CurrencyExchange>)

        fun updateExchangeCurrency(currencyFrom: CurrencyExchange, currencyTo: CurrencyExchange)

        fun updateCurrencyForEnd(currency: CurrencyExchange, item: CurrencyExchange)

        fun updateCurrencyForStart(currency: CurrencyExchange, item: CurrencyExchange)

        fun updatedCurrencyFromItem(currency: CurrencyExchange)

        fun updatedCurrencyToItem(currency: CurrencyExchange)

        fun clearExchangeFields()
    }

    interface Presenter: BaseContractPresenter<View> {
        fun onExchangeClick(
            currencyFrom: CurrencyExchange,
            currencyTo: CurrencyExchange
        )

        fun changeAmountFrom(from: CurrencyExchange)

        fun changeAmountTo(to: CurrencyExchange)

        fun updatedCurrencyFrom(currency: CurrencyExchange)

        fun updatedCurrencyTo(currency: CurrencyExchange)

    }
}