package com.jean.cinemapp.data.datasource.local.movie

import com.jean.cinemapp.data.database.entity.CastEntity
import com.jean.cinemapp.data.database.entity.MovieEntity
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.Resource

interface MovieLocalDataSource {

    suspend fun getRecommendedMovies(): Resource<List<Movie>>

    suspend fun getInCinemaMovies(): Resource<List<Movie>>

    suspend fun getNextReleaseMovies(): Resource<List<Movie>>

    suspend fun getMovieCast(movieId: String): Resource<List<Cast>>

    suspend fun saveMovie(movieEntity: MovieEntity)

    suspend fun saveCast(castEntity: CastEntity)
}