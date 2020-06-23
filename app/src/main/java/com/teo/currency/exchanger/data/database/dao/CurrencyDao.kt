package com.teo.currency.exchanger.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.teo.currency.exchanger.data.database.entity.CurrencyEntity

@Dao
interface CurrencyDao {

    @Query("SELECT * from currency_table")
    fun getLatestCurrency(): List<CurrencyEntity>

    @Query("SELECT * from currency_table WHERE name = :name")
    fun getCurrency(name: String): CurrencyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrency(currencyEntity: CurrencyEntity)

    @Query("DELETE FROM currency_table")
    fun deleteAll()
}