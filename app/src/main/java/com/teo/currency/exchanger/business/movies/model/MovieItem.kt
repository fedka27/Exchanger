package com.teo.currency.exchanger.business.movies.model

class MovieItem(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val mediaType: String?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val description: String?,
    val popularity: Float,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Float?,
    val voteCount: Int?
) {
    var isFavorite: Boolean = false

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieItem

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }


}

