package com.teo.currency.exchanger.components.main.movies.details

import com.teo.currency.exchanger.business.mappers.movie.MovieAmMapper
import com.teo.currency.exchanger.business.movies.MoviesInteractor
import com.teo.currency.exchanger.business.movies.MoviesInteractorImpl
import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.data.database.dao.MoviesDao
import com.teo.currency.exchanger.data.database.mappers.MovieEntityMapper
import com.teo.currency.exchanger.data.network.movies.MoviesApi
import com.teo.currency.exchanger.presentation.main.movies.MoviesPresenter
import com.teo.currency.exchanger.presentation.main.movies.details.MovieDetailsPresenter
import com.teo.currency.exchanger.utils.AppResourceProvider
import dagger.Module
import dagger.Provides

@Module
class MovieDetailsModule(
    private val movieItem: MovieItem
) {

    @MovieDetailsScope
    @Provides
    fun providePresenter(moviesInteractor: MoviesInteractor): MovieDetailsPresenter {
        return MovieDetailsPresenter(
            movieItem = movieItem,
            moviesInteractor = moviesInteractor
        )
    }
}