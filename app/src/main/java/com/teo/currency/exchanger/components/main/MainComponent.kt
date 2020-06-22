package com.teo.currency.exchanger.components.main

import com.teo.currency.exchanger.components.api.ApiComponent
import com.teo.currency.exchanger.components.app.AppComponent
import com.teo.currency.exchanger.presentation.main.MainActivity
import dagger.Component

@MainScope
@Component(
    modules = [
        MainModule::class
    ],
    dependencies = [
        ApiComponent::class
    ]
)
interface MainComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun module(mainModule: MainModule): Builder

        fun dependencyApi(apiComponent: ApiComponent): Builder

        fun build(): MainComponent
    }
}