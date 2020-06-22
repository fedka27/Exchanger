package com.teo.currency.exchanger.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.business.dto.Currency
import com.teo.currency.exchanger.components.providers.main.MainComponentProvider
import com.teo.currency.exchanger.presentation.base.BaseActivity
import com.teo.currency.exchanger.presentation.main.adapter.CurrencyAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<MainContract.View, MainContract.Presenter>(),
    MainContract.View {

    private val adapter = CurrencyAdapter()

    override fun initInjects() {
        MainComponentProvider.get().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    private fun initViews() {
        pager_currency_from.adapter = adapter
        pager_currency_from.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        pager_currency_to.adapter = adapter
        pager_currency_to.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }

    override fun updateAdapter(current: Currency, values: Collection<Currency>) {
        adapter.setCurrencyList(current, values)
    }
}