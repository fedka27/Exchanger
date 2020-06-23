package com.teo.currency.exchanger.presentation.main

interface CurrencyCalculator {
    fun calculateRateOneUnit(from: Double, to: Double): Double{
        return (1 / from) * to
    }

    fun calculateCurrencyByRate(amount: Double, rate: Double): Double{
        return amount / rate
    }
}