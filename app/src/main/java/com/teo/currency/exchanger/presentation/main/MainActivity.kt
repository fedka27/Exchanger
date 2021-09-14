package com.teo.currency.exchanger.presentation.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.viewpager2.widget.ViewPager2
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.components.providers.main.MainComponentProvider
import com.teo.currency.exchanger.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<MainContract.View, MainContract.Presenter>(),
    MainContract.View {

    override fun initInjects() {
        MainComponentProvider.mainComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}