package com.teo.currency.exchanger.components.main.movies

import com.teo.currency.exchanger.business.mappers.movie.MovieAmMapper
import com.teo.currency.exchanger.business.movies.MoviesInteractor
import com.teo.currency.exchanger.business.movies.MoviesInteractorImpl
import com.teo.currency.exchanger.data.database.dao.MoviesDao
import com.teo.currency.exchanger.data.database.mappers.MovieEntityMapper
import com.teo.currency.exchanger.data.network.movies.MoviesApi
import com.teo.currency.exchanger.presentation.main.movies.MoviesPresenter
import com.teo.currency.exchanger.utils.AppResourceProvider
import dagger.Module
import dagger.Provides

@Module
class MoviesModule {

    @MoviesScope
    @Provides
    fun providePresenter(moviesInteractor: MoviesInteractor): MoviesPresenter {
        return MoviesPresenter(moviesInteractor)
    }

    @MoviesScope
    @Provides
    fun provideMoviesInteractor(
        moviesApi: MoviesApi, //todo to repo
        moviesDao: MoviesDao,
        resourceProvider: AppResourceProvider,
        movieEntityMapper: MovieEntityMapper,
        movieAmMapper: MovieAmMapper
    ): MoviesInteractor {
        return MoviesInteractorImpl(
            moviesApi = moviesApi,
            moviesDao = moviesDao,
            resourceProvider = resourceProvider,
            movieAmMapper = movieAmMapper,
            movieEntityMapper = movieEntityMapper
        )
    }

}