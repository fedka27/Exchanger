package com.teo.currency.exchanger.components.providers.main.movies.details

import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.components.main.movies.DaggerMoviesComponent
import com.teo.currency.exchanger.components.main.movies.MoviesComponent
import com.teo.currency.exchanger.components.main.movies.MoviesModule
import com.teo.currency.exchanger.components.main.movies.details.MovieDetailsComponent
import com.teo.currency.exchanger.components.main.movies.details.MovieDetailsModule
import com.teo.currency.exchanger.components.providers.app.AppComponentProvider
import com.teo.currency.exchanger.components.providers.main.movies.MoviesComponentProvider

object MovieDetailsComponentProvider {
    private var movieDetailsComponent: MovieDetailsComponent? = null

    fun get(movieItem: MovieItem): MovieDetailsComponent {
        movieDetailsComponent = MoviesComponentProvider.get()
            .detailsSubcomponentBuilder()
            .module(MovieDetailsModule(movieItem))
            .build()

        return movieDetailsComponent!!
    }

    fun clear() {
        movieDetailsComponent = null
    }
}