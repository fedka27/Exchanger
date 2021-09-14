package com.teo.currency.exchanger.presentation.main

import android.os.Bundle
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.components.providers.main.MainComponentProvider
import com.teo.currency.exchanger.presentation.base.BaseActivity
import com.teo.currency.exchanger.presentation.main.exchanger.ExchangerFragment
import kotlinx.android.synthetic.main.activity_main.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class MainActivity : BaseActivity<MainView>(), MainView {

    @Inject
    lateinit var presenterProvider: Provider<MainPresenter>

    @ProvidePresenter
    fun providePresenter(): MainPresenter = presenterProvider.get()

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun initInjects() {
        MainComponentProvider.mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(content_container.id, ExchangerFragment())
            .commit()
    }

}