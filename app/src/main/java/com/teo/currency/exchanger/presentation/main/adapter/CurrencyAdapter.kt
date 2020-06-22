package com.teo.currency.exchanger.presentation.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.business.dto.Currency
import com.teo.currency.exchanger.presentation.extensions.inflateView
import kotlinx.android.synthetic.main.item_page_currency.view.*

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {
    private val list = arrayListOf<Currency>()

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setCurrencyList(current: Currency, values: Collection<Currency>) {
        this.list.clear()
        this.list.addAll(values)

        notifyDataSetChanged()
    }

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflateView(R.layout.item_page_currency)) {

        fun bind(currency: Currency) {
            with(itemView) {
                text_view_currency.text = currency.name
                text_view_amount.text = context.getString(
                    R.string.laber_amount, currency.amount,
                    currency.symbol
                )

                text_view_label_exchange.text = "???" //todo
            }
        }

    }
}