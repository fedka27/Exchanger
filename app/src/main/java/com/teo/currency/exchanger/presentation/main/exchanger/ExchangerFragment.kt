package com.teo.currency.exchanger.presentation.main.exchanger

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.components.providers.main.exchanger.ExchangerComponentProvider
import com.teo.currency.exchanger.presentation.base.BaseFragment
import com.teo.currency.exchanger.presentation.main.adapter.CurrencyAdapter
import kotlinx.android.synthetic.main.fragment_exchanger.*
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

class ExchangerFragment : BaseFragment(), ExchangerView {

    @Inject
    lateinit var presenterProvider: Provider<ExchangerPresenter>

    @ProvidePresenter
    fun providePresenter(): ExchangerPresenter = presenterProvider.get()

    @InjectPresenter
    lateinit var presenter: ExchangerPresenter

    private var adapterFrom = CurrencyAdapter()
    private var adapterTo = CurrencyAdapter()

    override fun initInjects() {
        ExchangerComponentProvider.exchangerComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exchanger, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        hideProgress()
        showMessageDialog(getString(R.string.error_load)) {
            activity?.finish() //todo change logic to reload
        }
    }

    override fun showErrorAmountZero() {
        showMessageDialog(getString(R.string.error_enter_amount_exchange))
    }

    override fun showErrorNotEnoughAmount() {
        showMessageDialog(getString(R.string.error_not_enough_amount))
    }
}