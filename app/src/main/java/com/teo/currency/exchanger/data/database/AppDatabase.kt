package com.teo.currency.exchanger.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.teo.currency.exchanger.data.database.converters.ListIntConverter
import com.teo.currency.exchanger.data.database.dao.CurrencyDao
import com.teo.currency.exchanger.data.database.dao.MoviesDao
import com.teo.currency.exchanger.data.database.entity.CurrencyEntity
import com.teo.currency.exchanger.data.database.entity.MovieEntity

@Database(
    entities = [
        CurrencyEntity::class,
        MovieEntity::class
    ],
    version = 3,
    exportSchema = false
)
@TypeConverters(ListIntConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun currencyDao(): CurrencyDao

    abstract fun moviesDao(): MoviesDao

}