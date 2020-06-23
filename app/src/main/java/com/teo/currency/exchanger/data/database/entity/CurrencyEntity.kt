package com.teo.currency.exchanger.data.database.entity

import androidx.room.*

@Entity(tableName = "currency_table")
data class CurrencyEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,

    @ColumnInfo(name = "symbol") val symbol: String,

    @ColumnInfo(name = "amount") val amount: Double,

    @ColumnInfo(name = "rate") val rate: Double
)