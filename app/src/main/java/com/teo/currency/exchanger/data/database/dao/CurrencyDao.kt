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
        currencyEntityFrom: CurrencyEntity,
        currencyEntityTo: CurrencyEntity,
        amountFrom: Double,
        amountTo: Double
    ) {
        Log.d(TAG, "exchangeCurrency from: $currencyEntityFrom")
        Log.d(TAG, "exchangeCurrency to: $currencyEntityTo")
        Log.d(TAG, "exchangeCurrency amount from: $amountFrom")
        Log.d(TAG, "exchangeCurrency amount to: $amountTo")
        //Validation
        if (amountFrom <= 0) {
            throw Errors.ExceptionZeroAmountValue()
        }

        if (currencyEntityFrom.amount < amountFrom) {
            throw Errors.ExceptionNotEnoughAmount()
        }

        val newAmountFrom = currencyEntityFrom.amount - amountFrom
        val newAmountTo = currencyEntityTo.amount + amountTo

        Log.d(TAG, "exchangeCurrency new amount from: $newAmountFrom")
        Log.d(TAG, "exchangeCurrency new amount to: $newAmountTo")

        insertOrUpdateCurrency(currencyEntityFrom.copy(amount = newAmountFrom))
        insertOrUpdateCurrency(currencyEntityTo.copy(amount = newAmountTo))
    }

}