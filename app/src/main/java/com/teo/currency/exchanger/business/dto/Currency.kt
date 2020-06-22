package com.teo.currency.exchanger.business.dto

data class Currency(
    val name: String,
    val value: Double,
    val amount: Double,
    val symbol: String
) {
}