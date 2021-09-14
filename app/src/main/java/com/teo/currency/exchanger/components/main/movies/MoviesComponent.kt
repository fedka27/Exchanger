package com.teo.currency.exchanger.components.main.movies

import com.teo.currency.exchanger.components.app.AppComponent
import com.teo.currency.exchanger.presentation.main.movies.MoviesFragment
import dagger.Component

@MoviesScope
@Component(
    dependencies = [AppComponent::class],
    modules = [MoviesModule::class]
)
interface MoviesComponent {
    fun inject(moviesFragment: MoviesFragment)

    @Component.Builder
    interface Builder {
        fun module(moviesModule: MoviesModule): Builder

        fun dependencyApp(appComponent: AppComponent): Builder

        fun build(): MoviesComponent
    }
}