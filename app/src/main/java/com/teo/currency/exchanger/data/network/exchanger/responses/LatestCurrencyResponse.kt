package com.teo.currency.exchanger.data.network.exchanger.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LatestCurrencyResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("date")
    val date: String, //yyyy-mm-dd
    @SerializedName("rates")
    val rates: Map<String, Double>
) : Serializable