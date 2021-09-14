package com.teo.currency.exchanger.presentation.main.movies

import com.teo.currency.exchanger.business.exchanger.model.CurrencyExchange
import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.presentation.base.BaseView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface MoviesView : BaseView{
        fun showProgress()

        fun hideProgress()

        fun displayContent()

        fun hideContent()

        fun setMovies(movies: List<MovieItem>)

        fun showErrorLoad()
}