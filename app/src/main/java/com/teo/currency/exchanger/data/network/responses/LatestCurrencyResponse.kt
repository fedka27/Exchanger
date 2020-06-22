package com.teo.currency.exchanger.data.network.responses

import java.io.Serializable

data class LatestCurrencyResponse(
    val base: String,
    val date: String, //yyyy-mm-dd
    val rates: Map<String, Double>
) : Serializable