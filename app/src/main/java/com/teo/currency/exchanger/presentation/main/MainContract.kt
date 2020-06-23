package com.teo.currency.exchanger.presentation.main

import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.presentation.base.BaseContractPresenter
import com.teo.currency.exchanger.presentation.base.BaseContractView

object MainContract {
    interface View : BaseContractView{

        fun setCurrencyList(
            baseCurrencyExchange: CurrencyExchange,
            values: Collection<CurrencyExchange>
        )

    }

    interface Presenter: BaseContractPresenter<View>, CurrencyCalculator{

    }
}