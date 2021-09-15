package com.teo.currency.exchanger.presentation.main.movies.details

import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MovieDetailsView : BaseView {

    fun fillDetails(movieItem: MovieItem)

}