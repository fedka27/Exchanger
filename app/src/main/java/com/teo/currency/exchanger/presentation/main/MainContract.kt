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

        fun updateCurrencyFrom(currency: CurrencyExchange)

        fun updateCurrencyTo(currency: CurrencyExchange)

        fun clearExchangeFields()
    }

    interface Presenter: BaseContractPresenter<View> {
        fun onExchangeClick(
            currencyFrom: CurrencyExchange,
            currencyTo: CurrencyExchange
        )

        fun changeAmountFrom(it: CurrencyExchange)

        fun changeAmountTo(it: CurrencyExchange)

        fun updatedCurrencyFrom(currency: CurrencyExchange)

        fun updatedCurrencyTo(currency: CurrencyExchange)

    }
}