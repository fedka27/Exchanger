package com.teo.currency.exchanger.data.database.dao

object Errors {
    class ExceptionNotEnoughAmount : RuntimeException()
    class ExceptionZeroAmountValue : RuntimeException()
}