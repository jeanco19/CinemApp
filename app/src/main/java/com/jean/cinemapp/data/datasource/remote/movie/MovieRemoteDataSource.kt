package com.jean.cinemapp.data.datasource.remote.movie

import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.Resource

interface MovieRemoteDataSource {

    suspend fun getRecommendedMovies(): Resource<List<Movie>>

    suspend fun getInCinemaMovies(): Resource<List<Movie>>

    suspend fun getNextReleaseMovies(): Resource<List<Movie>>

    suspend fun getMovieCast(movieId: String): Resource<List<Cast>>
}