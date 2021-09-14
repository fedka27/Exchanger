package com.teo.currency.exchanger.business.movies

import com.teo.currency.exchanger.business.movies.model.MovieItem
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

//todo implement paging
interface MoviesInteractor {

    fun getMovies(favorites: Boolean): Single<List<MovieItem>>

    fun addToFavorites(movieItem: MovieItem): Completable
}