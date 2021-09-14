package com.teo.currency.exchanger.data.network.movies

import com.teo.currency.exchanger.data.network.movies.responses.MoviesResult
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {
    @GET("search/multi")
    fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): Single<MoviesResult>
}