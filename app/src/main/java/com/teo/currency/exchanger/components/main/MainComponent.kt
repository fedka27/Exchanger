package com.teo.currency.exchanger.components.main

import com.teo.currency.exchanger.components.app.AppComponent
import com.teo.currency.exchanger.presentation.main.MainActivity
import dagger.Component

@MainScope
@Component(
    modules = [
        MainModule::class
    ],
    dependencies = [
        AppComponent::class
    ]
)
interface MainComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun module(mainModule: MainModule): Builder

        fun dependencyApp(appComponent: AppComponent): Builder

        fun build(): MainComponent
    }
}