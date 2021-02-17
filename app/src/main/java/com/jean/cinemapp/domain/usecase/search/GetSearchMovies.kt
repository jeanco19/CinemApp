package com.jean.cinemapp.domain.usecase.search

import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.Resource

interface GetSearchMovies {

    suspend fun invoke(query: String): Resource<List<Movie>>
}