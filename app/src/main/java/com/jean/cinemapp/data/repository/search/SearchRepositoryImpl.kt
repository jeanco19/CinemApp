package com.jean.cinemapp.data.repository.search

import com.jean.cinemapp.data.datasource.remote.search.SearchRemoteDataSource
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.domain.repository.search.SearchRepository
import com.jean.cinemapp.utils.Connectivity
import com.jean.cinemapp.utils.ErrorTypes
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val searchRemoteDataSource: SearchRemoteDataSource): SearchRepository {

    override suspend fun getSearchMovies(query: String): Resource<List<Movie>> {
        return if (Connectivity.isNetworkAvailable()) {
            searchRemoteDataSource.getSearchMovies(query)
        } else {
            Resource.Error(ErrorTypes.WITHOUT_CONNECTION)
        }
    }
}