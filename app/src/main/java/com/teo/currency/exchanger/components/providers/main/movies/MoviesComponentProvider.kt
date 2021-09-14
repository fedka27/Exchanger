package com.teo.currency.exchanger.components.providers.main.movies

import com.teo.currency.exchanger.components.main.movies.DaggerMoviesComponent
import com.teo.currency.exchanger.components.main.movies.MoviesComponent
import com.teo.currency.exchanger.components.main.movies.MoviesModule
import com.teo.currency.exchanger.components.providers.app.AppComponentProvider

object MoviesComponentProvider {
    val moviesComponent: MoviesComponent by lazy {
        DaggerMoviesComponent.builder()
            .module(MoviesModule())
            .dependencyApp(AppComponentProvider.appComponent)
            .build()
    }
}