package com.teo.currency.exchanger.presentation.main

import com.teo.currency.exchanger.presentation.base.BaseContractPresenter
import com.teo.currency.exchanger.presentation.base.BaseContractView

object MainContract {
    interface View : BaseContractView{

    }

    interface Presenter: BaseContractPresenter<View>{

    }
}