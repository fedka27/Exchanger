package com.teo.currency.exchanger.presentation.main

import com.teo.currency.exchanger.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : BaseView {
}