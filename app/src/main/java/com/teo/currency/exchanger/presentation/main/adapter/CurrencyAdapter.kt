package com.teo.currency.exchanger.presentation.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.teo.currency.exchanger.business.dto.CurrencyExchange

class CurrencyAdapter(
) : RecyclerView.Adapter<CurrencyViewHolder>() {

    private var currencyExchange: CurrencyExchange? = null

    private val list = arrayListOf<CurrencyExchange>()

    var exchangeChangeListener: ((CurrencyExchange) -> Unit)? = null

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder(parent) {
            exchangeChangeListener?.invoke(it)
        }
    }

    override fun onBindViewHolder(holderCurrency: CurrencyViewHolder, position: Int) {
        holderCurrency.bind(currencyExchange ?: list.first(), list[position])
    }

    fun updateExchangeCurrency(currencyExchangeExchange: CurrencyExchange) {
        this.currencyExchange = currencyExchangeExchange
        notifyDataSetChanged()
    }

    fun setCurrencyList(values: Collection<CurrencyExchange>) {

        val diffCallback = CurrencyDiffCallback(list, values.toList())

        val result = DiffUtil.calculateDiff(diffCallback)
        result.dispatchUpdatesTo(this)

        this.list.clear()
        this.list.addAll(values)
//        notifyDataSetChanged()
    }

    fun getItem(position: Int): CurrencyExchange = list[position]

    fun updateItem(currency: CurrencyExchange) {
        val index = list.indexOf(currency)
        if (index != -1) {
            list[index].rate = currency.rate
            notifyItemChanged(index)
        }
    }

    fun clearField() {
        notifyDataSetChanged()
    }

    class CurrencyDiffCallback(
        private val oldList: List<CurrencyExchange>,
        private val newList: List<CurrencyExchange>

    ) : DiffUtil.Callback() {
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].name == newList[newItemPosition].name
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

    }
}