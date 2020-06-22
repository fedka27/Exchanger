package com.teo.currency.exchanger.presentation.main

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.components.providers.main.MainComponentProvider
import com.teo.currency.exchanger.presentation.base.BaseActivity
import com.teo.currency.exchanger.presentation.main.adapter.CurrencyAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<MainContract.View, MainContract.Presenter>(),
    MainContract.View {

    private var adapterFrom: CurrencyAdapter? = null
    private var adapterTo: CurrencyAdapter? = null

    override fun initInjects() {
        MainComponentProvider.get().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun initCurrencyFromViews(baseCurrencyExchange: CurrencyExchange) {
        adapterFrom = CurrencyAdapter(baseCurrencyExchange)
        pager_currency_from.adapter = adapterFrom
        pager_currency_from.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        pager_currency_from.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val currency = adapterFrom!!.getItem(position)
                adapterTo?.updateExchangeCurrency(currency)
            }
        })
    }

    private fun initCurrencyToViews(baseCurrencyExchange: CurrencyExchange) {
        adapterTo = CurrencyAdapter(baseCurrencyExchange)
        pager_currency_to.adapter = adapterTo
        pager_currency_to.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        pager_currency_to.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val currency = adapterTo!!.getItem(position)
                adapterFrom?.updateExchangeCurrency(currency)
            }
        })
    }

    override fun setCurrencyList(baseCurrencyExchange: CurrencyExchange, values: Collection<CurrencyExchange>) {
        //todo refactor
        if (adapterFrom == null) {
            initCurrencyFromViews(baseCurrencyExchange)
        }
        if (adapterTo == null) {
            initCurrencyToViews(baseCurrencyExchange)
        }

        adapterFrom!!.setCurrencyList(values)
        adapterTo!!.setCurrencyList(values)
    }
}