package com.teo.currency.exchanger.business.mappers.movie

import com.teo.currency.exchanger.utils.BaseMapper
import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.data.network.movies.responses.dto.MovieItemAm
import javax.inject.Inject

class MovieAmMapper @Inject constructor(): BaseMapper<MovieItemAm, MovieItem>() {

    override fun map(from: MovieItemAm): MovieItem {
        return from.let {
            MovieItem(
                id = it.id,
                adult = it.adult,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds,
                mediaType = it.mediaYype,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                description = it.description,
                popularity = it.popularity,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        }
    }
}