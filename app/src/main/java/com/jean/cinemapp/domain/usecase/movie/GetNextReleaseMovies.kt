package com.jean.cinemapp.domain.usecase.movie

import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.Resource

interface GetNextReleaseMovies {

    suspend fun invoke(): Resource<List<Movie>>
}