package com.jean.cinemapp.data.datasource.local.movie

import com.jean.cinemapp.data.database.CinemaDao
import com.jean.cinemapp.data.database.entity.CastEntity
import com.jean.cinemapp.data.database.entity.MovieEntity
import com.jean.cinemapp.data.mappers.toCast
import com.jean.cinemapp.data.mappers.toMovie
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.Constants.MOVIE_IN_CINEMA
import com.jean.cinemapp.utils.Constants.MOVIE_NEXT_RELEASE
import com.jean.cinemapp.utils.Constants.MOVIE_RECOMMENDED
import com.jean.cinemapp.utils.ErrorTypes
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(private val cinemaDao: CinemaDao): MovieLocalDataSource {

    override suspend fun getRecommendedMovies(): Resource<List<Movie>> =
        handleResponse(cinemaDao.getAllMovies(), MOVIE_RECOMMENDED)

    override suspend fun getInCinemaMovies(): Resource<List<Movie>> =
        handleResponse(cinemaDao.getAllMovies(), MOVIE_IN_CINEMA)

    override suspend fun getNextReleaseMovies(): Resource<List<Movie>> =
        handleResponse(cinemaDao.getAllMovies(), MOVIE_NEXT_RELEASE)

    private fun handleResponse(response: List<MovieEntity>, movieType: String): Resource<List<Movie>> {
        return Resource.Error(ErrorTypes.WITHOUT_CONNECTION, data = response.filter {
            it.type == movieType
        }.map { movieEntity ->
            movieEntity.toMovie()
        })
    }

    override suspend fun getMovieCast(movieId: String): Resource<List<Cast>> {
        val castList = cinemaDao.getMovieCast(movieId.toInt())
        return Resource.Error(ErrorTypes.WITHOUT_CONNECTION, data = castList.map { castEntity ->
            castEntity.toCast()
        })
    }

    override suspend fun saveMovie(movieEntity: MovieEntity) {
        cinemaDao.saveMovie(movieEntity)
    }

    override suspend fun saveCast(castEntity: CastEntity) {
        cinemaDao.saveCast(castEntity)
    }
}