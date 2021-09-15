package com.teo.currency.exchanger.presentation.main.movies.details

import android.util.Log
import com.teo.currency.exchanger.business.exchanger.model.CurrencyExchange
import com.teo.currency.exchanger.business.movies.MoviesInteractor
import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.data.database.dao.Errors
import com.teo.currency.exchanger.presentation.base.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import moxy.InjectViewState

@InjectViewState
class MovieDetailsPresenter(
    private val movieItem: MovieItem,
    private val moviesInteractor: MoviesInteractor
) : BasePresenter<MovieDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.fillDetails(movieItem)

        //todo test only
        moviesInteractor.addToFavorites(movieItem)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}