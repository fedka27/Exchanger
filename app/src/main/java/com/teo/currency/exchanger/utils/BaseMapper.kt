package com.teo.currency.exchanger.utils

abstract class BaseMapper<FROM, TO> {
    abstract fun map(from: FROM): TO

    fun mapList(froms: List<FROM>): List<TO> = froms.map { map(it) }
}