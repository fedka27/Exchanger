package com.teo.currency.exchanger.presentation.main

import com.teo.currency.exchanger.business.dto.Currency
import com.teo.currency.exchanger.presentation.base.BaseContractPresenter
import com.teo.currency.exchanger.presentation.base.BaseContractView

object MainContract {
    interface View : BaseContractView{

        fun updateAdapter(current: Currency, values: Collection<Currency>)

    }

    interface Presenter: BaseContractPresenter<View>{

    }
}