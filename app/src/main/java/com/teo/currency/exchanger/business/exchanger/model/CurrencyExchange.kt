package com.teo.currency.exchanger.business.exchanger.model


data class CurrencyExchange(
    val name: String,
    val symbol: String,
    val rate: Double,
    var amount: Double,
    var amountAtRate: Double
) {

    fun calculateRateOneUnit(to: CurrencyExchange): Double {
        return (1.toDouble() / rate) * to.rate
    }

    fun calculateCurrencyByRate(rate: Double): Double {
        return amountAtRate * rate
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CurrencyExchange

        if (name != other.name) return false
        if (symbol != other.symbol) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + symbol.hashCode()
        return result
    }
}