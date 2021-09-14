package com.teo.currency.exchanger.business.mappers.movie

import android.content.Context
import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.utils.BaseMapper
import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.data.network.movies.responses.dto.MovieItemAm
import com.teo.currency.exchanger.utils.AppResourceProvider
import javax.inject.Inject

class MovieAmMapper @Inject constructor(
    resourceProvider: AppResourceProvider
) : BaseMapper<MovieItemAm, MovieItem>() {

    private val imageBaseUrl = resourceProvider.getString(R.string.base_url_movies_image)

    override fun map(from: MovieItemAm): MovieItem {
        return from.let {
            MovieItem(
                id = it.id,
                adult = it.adult,
                backdropPath = getImagePath(it.backdropPath),
                genreIds = it.genreIds,
                mediaType = it.mediaYype,
                originalLanguage = it.originalLanguage,
                originalTitle = it.originalTitle,
                description = it.description,
                popularity = it.popularity,
                posterPath = getImagePath(it.posterPath),
                releaseDate = it.releaseDate,
                title = it.title,
                video = it.video,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount
            )
        }
    }

    private fun getImagePath(path: String?): String? {
        return if (path == null) null
        else "$imageBaseUrl$path"
    }
}