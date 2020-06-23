package com.teo.currency.exchanger.data.database.dao

import android.util.Log
import androidx.room.*
import com.teo.currency.exchanger.data.database.entity.CurrencyEntity

@Dao
abstract class CurrencyDao {
    private val TAG: String = CurrencyDao::class.java.simpleName

    @Query("SELECT * from currency_table")
    abstract fun getLatestCurrency(): List<CurrencyEntity>

    @Query("SELECT * from currency_table WHERE name = :name")
    abstract fun getCurrency(name: String): CurrencyEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertOrUpdateCurrency(currencyEntity: CurrencyEntity)

    @Query("DELETE FROM currency_table")
    abstract fun deleteAll()

    @Transaction
    open fun exchangeCurrency(
        currencyFrom: String,
        currencyTo: String,
        amountFromAtRate: Double,
        amountFromToAtRate: Double
    ) {
        Log.d(TAG, "exchangeCurrency from: $currencyFrom")
        Log.d(TAG, "exchangeCurrency to: $currencyTo")
        Log.d(TAG, "exchangeCurrency amount from: $amountFromAtRate")
        Log.d(TAG, "exchangeCurrency amount to: $amountFromToAtRate")

        //First step - withdraw currency
        val entityFrom = getCurrency(currencyFrom)!!

        //Check for correct withdrawal
        if (amountFromAtRate <= 0) {
            throw Errors.ExceptionZeroAmountValue()
        }

        if (entityFrom.amount < amountFromAtRate) {
            throw Errors.ExceptionNotEnoughAmount()
        }

        //Withdrawal
        val newAmountFrom = entityFrom.amount - amountFromAtRate
        //Save currency from
        insertOrUpdateCurrency(entityFrom.copy(amount = newAmountFrom))

        //Second step - receipt currency
        val entityTo = getCurrency(currencyTo)!!
        val newAmountTo = entityTo.amount + amountFromToAtRate

        Log.d(TAG, "exchangeCurrency new amount from: $newAmountFrom")
        Log.d(TAG, "exchangeCurrency new amount to: $newAmountTo")

        //Save currency to
        insertOrUpdateCurrency(entityTo.copy(amount = newAmountTo))
    }

}