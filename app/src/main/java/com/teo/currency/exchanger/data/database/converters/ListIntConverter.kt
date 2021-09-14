package com.teo.currency.exchanger.data.database.converters

import androidx.room.TypeConverter
import java.util.*

class ListIntConverter {

    @TypeConverter
    fun toLong(ints: List<Int>?): String? {
        if (ints.isNullOrEmpty()) return null
        return ints.joinToString(separator = ",")
    }

    @TypeConverter
    fun fromLong(ints: String?): List<Int>? {
        if (ints.isNullOrEmpty()) return null
        return ints.split(",").map { it.toInt() }
    }

}