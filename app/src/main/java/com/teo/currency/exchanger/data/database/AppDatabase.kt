package com.teo.currency.exchanger.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.teo.currency.exchanger.data.database.dao.CurrencyDao
import com.teo.currency.exchanger.data.database.entity.CurrencyEntity

@Database(
    entities = [CurrencyEntity::class],
    version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

}