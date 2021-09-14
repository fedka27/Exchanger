package com.teo.currency.exchanger.data.database.mappers

import com.teo.currency.exchanger.utils.BaseMapper
import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.data.database.entity.MovieEntity
import com.teo.currency.exchanger.data.network.movies.responses.dto.MovieItemAm
import com.teo.currency.exchanger.utils.BaseMapperReverse
import javax.inject.Inject

class MovieEntityMapper @Inject constructor() : BaseMapperReverse<MovieEntity, MovieItem>() {

    override fun map(from: MovieEntity): MovieItem {
        return from.let {
            MovieItem(
                id = it.id,
                adult = it.adult,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds,
                mediaType = it.mediaType,
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

    override fun reverse(from: MovieItem): MovieEntity {
        return from.let {
            MovieEntity(
                id = it.id,
                adult = it.adult,
                backdropPath = it.backdropPath,
                genreIds = it.genreIds,
                mediaType = it.mediaType,
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