package com.teo.currency.exchanger.presentation.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.presentation.main.CurrencyCalculator

class CurrencyAdapter(
    private var currencyExchange: CurrencyExchange,
    private val currencyCalculator: CurrencyCalculator
) : RecyclerView.Adapter<CurrencyViewHolder>() {

    private val list = arrayListOf<CurrencyExchange>()

    private var exchangeAmount: Double = 0.0
    private var lastExchangeAmount: Double? = null
    var exchangeChangeListener: ((Double) -> Unit)? = null

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(parent, currencyCalculator) {
            lastExchangeAmount = it
            exchangeChangeListener?.invoke(it)
        }
    }

    override fun onBindViewHolder(holderCurrency: CurrencyViewHolder, position: Int) {
        holderCurrency.bind(currencyExchange, list[position], lastExchangeAmount, exchangeAmount)
    }

    fun updateExchangeCurrency(currencyExchangeExchange: CurrencyExchange) {
        this.currencyExchange = currencyExchangeExchange
        notifyDataSetChanged()
    }

    fun updateExchangeAmount(exchangeAmount: Double) {
        this.exchangeAmount = exchangeAmount
        this.lastExchangeAmount = null
        notifyDataSetChanged()
    }

    fun setCurrencyList(values: Collection<CurrencyExchange>) {
        this.list.clear()
        this.list.addAll(values)

        notifyDataSetChanged()
    }

    fun getItem(position: Int): CurrencyExchange = list[position]

}