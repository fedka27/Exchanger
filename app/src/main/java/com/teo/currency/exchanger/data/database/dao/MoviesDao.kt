package com.teo.currency.exchanger.data.database.dao

import androidx.room.*
import com.teo.currency.exchanger.data.database.entity.MovieEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addToFavoriteMovie(movie: MovieEntity)

    @Query("DELETE FROM movies WHERE id = :movieId")
    fun removeFavorite(movieId: Int)

    @Query("SELECT * FROM movies")
    fun getAllFavorites(): List<MovieEntity>

    @Query("DELETE FROM movies")
    fun removeAll()
}