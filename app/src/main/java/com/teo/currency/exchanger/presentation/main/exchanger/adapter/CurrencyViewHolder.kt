package com.teo.currency.exchanger.presentation.main.exchanger.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.business.exchanger.model.CurrencyExchange
import com.teo.currency.exchanger.presentation.extensions.inflateView
import kotlinx.android.synthetic.main.item_page_currency.view.*

class CurrencyViewHolder(
    parent: ViewGroup,
    private val exchangeChangeListener: ((CurrencyExchange) -> Unit)? = null
) :
    RecyclerView.ViewHolder(parent.inflateView(R.layout.item_page_currency)) {

    private lateinit var exchangerItem: CurrencyExchange

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {}

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {
            val newExchangeAmount = text.toString()
                .replace(",", ".")
                .toDoubleOrNull()

            exchangerItem.amountAtRate = newExchangeAmount ?: 0.0

            exchangeChangeListener?.invoke(exchangerItem)
        }
    }

    fun bind(
        exchangerAnother: CurrencyExchange,
        exchangerItem: CurrencyExchange
    ) {
        this.exchangerItem = exchangerItem

        with(itemView) {
            //Currency symbol
            text_view_currency.text = exchangerItem.name

            //Available balance
            text_view_amount.text = context.getString(
                R.string.main_label_amount,
                exchangerItem.amount,
                exchangerItem.symbol
            )

            val rate = exchangerItem.calculateRateOneUnit(exchangerAnother)

            //Remove old change listener after update
            edit_text_exchange.removeTextChangedListener(textWatcher)

            //display last input value or calculate amount to exchange
            //Set calculated amount by rate
            edit_text_exchange.setText(String.format("%.2f", exchangerItem.amountAtRate))

            //Listener of amount changes
            edit_text_exchange.addTextChangedListener(textWatcher)

            //Label about one currency relative to another
            val labelOfExchange = String.format(
                "%s1 = %s%.2f",
                exchangerItem.symbol,
                exchangerAnother.symbol,
                rate
            )
            text_view_label_exchange.text = labelOfExchange
        }
    }

}