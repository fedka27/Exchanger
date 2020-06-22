package com.teo.currency.exchanger.components.app

import dagger.Component

@AppScope
@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun module(appModule: AppModule): Builder

        fun build(): AppComponent
    }
}