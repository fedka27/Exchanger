package com.teo.currency.exchanger.business.dto

import java.util.*

data class CurrencyExchange(
    val currency: Currency,
    val value: Double,
    var amount: Double
) {
}