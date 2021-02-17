package com.jean.cinemapp.data.datasource.local.movie

import com.jean.cinemapp.data.database.entity.MovieEntity
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.Resource

interface MovieLocalDataSource {

    suspend fun getRecommendedMovies(): Resource<List<Movie>>

    suspend fun getInCinemaMovies(): Resource<List<Movie>>

    suspend fun getNextReleaseMovies(): Resource<List<Movie>>

    suspend fun saveMovie(movieEntity: MovieEntity)
}