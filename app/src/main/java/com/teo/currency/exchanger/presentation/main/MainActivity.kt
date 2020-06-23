package com.teo.currency.exchanger.presentation.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
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

    private var adapterFrom = CurrencyAdapter()
    private var adapterTo = CurrencyAdapter()

    override fun initInjects() {
        MainComponentProvider.get().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCurrencyFromViews()
        initCurrencyToViews()

        button_exchange.setOnClickListener {
            val currencyFrom = adapterFrom.getItem(pager_currency_from.currentItem)
            val currencyTo = adapterTo.getItem(pager_currency_to.currentItem)

            presenter.onExchangeClick(
                currencyFrom,
                currencyTo
            )
        }
    }

    private fun initCurrencyFromViews() {
        pager_currency_from.adapter = adapterFrom
        pager_currency_from.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        adapterFrom.exchangeChangeListener = {
            presenter.changeAmountFrom(it)
        }
        pager_currency_from.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val currency = adapterFrom.getItem(position)
                presenter.updatedCurrencyFrom(currency)
            }
        })
    }

    private fun initCurrencyToViews() {
        pager_currency_to.adapter = adapterTo
        pager_currency_to.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        adapterTo.exchangeChangeListener = {
            presenter.changeAmountTo(it)
        }
        pager_currency_to.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                val currency = adapterTo.getItem(position)
                presenter.updatedCurrencyTo(currency)
            }
        })
    }

    override fun showProgress() {
        progress_bar.visibility = VISIBLE
    }

    override fun hideProgress() {
        progress_bar.visibility = GONE
    }

    override fun displayContent() {
        content_container.visibility = VISIBLE
        button_exchange.visibility = VISIBLE
    }

    override fun hideContent() {
        content_container.visibility = GONE
        button_exchange.visibility = GONE
    }

    override fun setCurrencyList(values: List<CurrencyExchange>) {
        adapterFrom.setCurrencyList(values)
        adapterTo.setCurrencyList(values)
    }

    override fun updateExchangeCurrency(
        currencyFrom: CurrencyExchange,
        currencyTo: CurrencyExchange
    ) {

        adapterFrom.updateItem(currencyFrom)
        adapterTo.updateItem(currencyFrom)

        adapterFrom.updateItem(currencyTo)
        adapterTo.updateItem(currencyTo)
    }

    override fun updateCurrencyForEnd(currency: CurrencyExchange, item: CurrencyExchange) {
        adapterTo.updateExchangeCurrency(currency, item)
    }

    override fun updateCurrencyForStart(currency: CurrencyExchange, item: CurrencyExchange) {
        adapterFrom.updateExchangeCurrency(currency, item)
    }

    override fun updatedCurrencyFromItem(currency: CurrencyExchange) {
        pager_currency_from.post {
            adapterFrom.updateItem(currency)
        }
    }

    override fun updatedCurrencyToItem(currency: CurrencyExchange) {
        pager_currency_to.post {
            adapterTo.updateItem(currency)
        }
    }

    override fun showAllCurrencyAmount(list: List<CurrencyExchange>) {
        showMessageDialog(
            getString(
                R.string.main_actual_balance,
                list.joinToString(
                    separator = "\n",
                    transform = { "${it.name} - ${it.symbol}: ${String.format("%.2f", it.amount)}" }
                )
            )
        )
    }

    override fun showErrorLoad() {
        showMessageDialog(getString(R.string.error_load)) {
            finish()
        }
    }

    override fun showErrorAmountZero() {
        showMessageDialog(getString(R.string.error_enter_amount_exchange))
    }

    override fun showErrorNotEnoughAmount() {
        showMessageDialog(getString(R.string.error_not_enough_amount))
    }
}