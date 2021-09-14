package com.teo.currency.exchanger.business.movies

import com.teo.currency.exchanger.R
import com.teo.currency.exchanger.business.mappers.movie.MovieAmMapper
import com.teo.currency.exchanger.business.movies.model.MovieItem
import com.teo.currency.exchanger.data.database.dao.MoviesDao
import com.teo.currency.exchanger.data.database.entity.MovieEntity
import com.teo.currency.exchanger.data.database.mappers.MovieEntityMapper
import com.teo.currency.exchanger.data.network.movies.MoviesApi
import com.teo.currency.exchanger.utils.AppResourceProvider
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class MoviesInteractorImpl(
    private val moviesApi: MoviesApi,
    private val moviesDao: MoviesDao,
    private val resourceProvider: AppResourceProvider,
    private val movieAmMapper: MovieAmMapper,
    private val movieEntityMapper: MovieEntityMapper
) : MoviesInteractor {

    override fun getMovies(): Single<List<MovieItem>> {

        val moviesSingle = moviesApi.getMovies(
            apiKey = resourceProvider.getString(R.string.api_key_movies),
            query = "300"
        ).map { movieAmMapper.mapList(it.results) }

        val favorites = moviesDao.getAllFavorites()
            .let { movieEntityMapper.mapList(it) }

        return moviesSingle.map { movies ->
            movies.onEach { movie ->
                movie.isFavorite = favorites.contains(movie)
            }
        }
    }

    override fun addToFavorites(movieItem: MovieItem): Completable {
        return Completable.fromCallable {
            moviesDao.addToFavoriteMovie(
                movie = movieEntityMapper.reverse(movieItem)
            )
        }
    }
}