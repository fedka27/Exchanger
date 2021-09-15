package com.teo.currency.exchanger.components.providers.main.movies

import com.teo.currency.exchanger.components.main.movies.DaggerMoviesComponent
import com.teo.currency.exchanger.components.main.movies.MoviesComponent
import com.teo.currency.exchanger.components.main.movies.MoviesModule
import com.teo.currency.exchanger.components.providers.app.AppComponentProvider

object MoviesComponentProvider {
    private var moviesComponent: MoviesComponent? = null

    fun get(showFavorites: Boolean): MoviesComponent {
        moviesComponent = DaggerMoviesComponent.builder()
            .module(MoviesModule(showFavorites))
            .dependencyApp(AppComponentProvider.appComponent)
            .build()

        return moviesComponent!!
    }

    fun get() = moviesComponent!!
}