package com.teo.currency.exchanger.data.database.dao

import java.lang.RuntimeException

object Errors {
    class ExceptionNotEnoughAmount(): RuntimeException()
    class ExceptionZeroAmountValue(): RuntimeException()
}