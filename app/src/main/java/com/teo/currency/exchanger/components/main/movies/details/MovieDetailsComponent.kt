package com.teo.currency.exchanger.components.main.movies.details

import com.teo.currency.exchanger.presentation.main.movies.details.MovieDetailsFragment
import dagger.Subcomponent

@MovieDetailsScope
@Subcomponent(modules = [MovieDetailsModule::class])
interface MovieDetailsComponent {
    fun inject(movieDetailsFragment: MovieDetailsFragment)

    @Subcomponent.Builder
    interface Builder {
        fun module(moviesModule: MovieDetailsModule): Builder

        fun build(): MovieDetailsComponent
    }
}