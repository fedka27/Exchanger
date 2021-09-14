package com.teo.currency.exchanger.data.network.movies.responses

import com.google.gson.annotations.SerializedName
import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.data.network.movies.responses.dto.MovieItemAm

class MoviesResult(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieItemAm>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
