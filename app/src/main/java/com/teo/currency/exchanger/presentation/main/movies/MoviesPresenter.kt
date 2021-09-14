package com.teo.currency.exchanger.presentation.main.movies

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
class MoviesPresenter(
    private val moviesInteractor: MoviesInteractor
) : BasePresenter<MoviesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadMovies()
    }

    private fun loadMovies() {
        compositeDisposable.add(moviesInteractor
            .getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showProgress() }
            .doFinally { viewState.hideProgress() }
            .subscribe(this::handleCurrencyUpdated) {
                it.printStackTrace()
                viewState.showErrorLoad()
            }
        )
    }

    private fun handleCurrencyUpdated(movies: List<MovieItem>) {
        viewState.displayContent()
        viewState.setMovies(movies)
    }

    fun onMovieClick(movieItem: MovieItem) {
        //TODO("Not yet implemented")
    }
}