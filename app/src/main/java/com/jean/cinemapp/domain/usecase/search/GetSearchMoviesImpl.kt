package com.jean.cinemapp.domain.usecase.search

import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.domain.repository.search.SearchRepository
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class GetSearchMoviesImpl @Inject constructor(private val searchRepository: SearchRepository): GetSearchMovies {

    override suspend fun invoke(query: String): Resource<List<Movie>> =
        searchRepository.getSearchMovies(query)
}