package com.teo.currency.exchanger.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class CurrencyEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,

    @ColumnInfo(name = "symbol") val symbol: String,

    @ColumnInfo(name = "amount") val amount: Double,

    @ColumnInfo(name = "rate") val rate: Double
)