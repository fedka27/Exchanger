package com.teo.currency.exchanger.presentation.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.presentation.extensions.inflateView
import kotlinx.android.synthetic.main.item_page_currency.view.*

class CurrencyAdapter(
    private var currencyExchange: CurrencyExchange
) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private val list = arrayListOf<CurrencyExchange>()

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun updateExchangeCurrency(currencyExchangeExchange: CurrencyExchange) {
        this.currencyExchange = currencyExchangeExchange
        notifyDataSetChanged()
    }

    fun setCurrencyList(values: Collection<CurrencyExchange>) {
        this.list.clear()
        this.list.addAll(values)

        notifyDataSetChanged()
    }

    fun getItem(position: Int): CurrencyExchange = list[position]

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflateView(R.layout.item_page_currency)) {

        fun bind(item: CurrencyExchange) {
            with(itemView) {
                //Currency symbol
                text_view_currency.text = item.currency.currencyCode
                //Available balance
                text_view_amount.text = context.getString(
                    R.string.label_amount, item.amount,
                    item.currency.symbol
                )

                //todo refactor to formula
                val rates = (1 / item.value) * currencyExchange.value

                val labelOfExchange = String.format(
                    "%s1 = %s%.2f",
                    item.currency.symbol,
                    currencyExchange.currency.symbol,
                    rates
                )
                text_view_label_exchange.text = labelOfExchange
            }
        }

    }
}