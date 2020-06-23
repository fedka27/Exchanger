package com.teo.currency.exchanger.presentation.main.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.business.dto.CurrencyExchange
import com.teo.currency.exchanger.presentation.extensions.inflateView
import com.teo.currency.exchanger.presentation.main.CurrencyCalculator
import kotlinx.android.synthetic.main.item_page_currency.view.*

class CurrencyViewHolder(
    parent: ViewGroup,
    private val currencyCalculator: CurrencyCalculator,
    private val exchangeChangeListener: ((Double) -> Unit)? = null
) :
    RecyclerView.ViewHolder(parent.inflateView(R.layout.item_page_currency)) {

    fun bind(
        currencyExchange: CurrencyExchange,
        item: CurrencyExchange,
        exchangeAmountLast: Double?,
        exchangeAmount: Double
    ) {
        with(itemView) {
            //Currency symbol
            text_view_currency.text = item.currency.currencyCode

            //Available balance
            text_view_amount.text = context.getString(
                R.string.label_amount,
                item.amount,
                item.currency.symbol
            )

            val rate = currencyCalculator
                .calculateRateOneUnit(item.value, currencyExchange.value)

            //display last input value or calculate amount to exchange
            val amount = exchangeAmountLast ?: currencyCalculator
                .calculateCurrencyByRate(exchangeAmount, rate)

            //Set calculated amount by rate
            edit_text_exchange.setText(String.format("%.2f", amount))

            //Listener of amount changes
            edit_text_exchange.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun onTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {
                    if (edit_text_exchange.isFocused) {
                        val newExchangeAmount = text.toString().toDoubleOrNull() ?: 0.0

                        exchangeChangeListener?.invoke(newExchangeAmount)
                    }
                }
            })

            //Label about one currency relative to another
            val labelOfExchange = String.format(
                "%s1 = %s%.2f",
                item.currency.symbol,
                currencyExchange.currency.symbol,
                rate
            )
            text_view_label_exchange.text = labelOfExchange
        }
    }

}