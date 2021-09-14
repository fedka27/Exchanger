package com.teo.currency.exchanger.components.main.exchanger

import com.teo.currency.exchanger.components.app.AppComponent
import com.teo.currency.exchanger.presentation.main.exchanger.ExchangerFragment
import dagger.Component

@ExchangerScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ExchangerModule::class]
)
interface ExchangerComponent {
    fun inject(exchangerFragment: ExchangerFragment)

    @Component.Builder
    interface Builder {
        fun module(exchangerModule: ExchangerModule): Builder

        fun dependencyApp(appComponent: AppComponent): Builder

        fun build(): ExchangerComponent
    }
}