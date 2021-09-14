package com.teo.currency.exchanger.utils

abstract class BaseMapperReverse<FROM, TO>: BaseMapper<FROM, TO>() {
    abstract fun reverse(from: TO): FROM
}