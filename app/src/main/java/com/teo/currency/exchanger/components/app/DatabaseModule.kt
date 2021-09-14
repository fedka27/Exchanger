package com.teo.currency.exchanger.components.app

import android.content.Context
import androidx.room.Room
import com.teo.currency.exchanger.data.database.AppDatabase
import com.teo.currency.exchanger.data.database.dao.CurrencyDao
import com.teo.currency.exchanger.data.database.dao.MoviesDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {


    @AppScope
    @Provides
    fun provideAppDatabase(applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "currency_exchanger_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @AppScope
    @Provides
    fun provideCurrencyDao(appDatabase: AppDatabase): CurrencyDao = appDatabase.currencyDao()

    @AppScope
    @Provides
    fun provideMoviesDao(appDatabase: AppDatabase): MoviesDao = appDatabase.moviesDao()

}