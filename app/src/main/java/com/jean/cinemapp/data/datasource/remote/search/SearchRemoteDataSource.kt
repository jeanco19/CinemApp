package com.jean.cinemapp.data.datasource.remote.search

import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.Resource

interface SearchRemoteDataSource {

    suspend fun getSearchMovies(query: String): Resource<List<Movie>>
}