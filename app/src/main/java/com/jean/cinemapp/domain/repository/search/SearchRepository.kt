package com.jean.cinemapp.domain.repository.search

import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.Resource

interface SearchRepository {

    suspend fun getSearchMovies(query: String): Resource<List<Movie>>
}