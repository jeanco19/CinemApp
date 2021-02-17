package com.jean.cinemapp.data.datasource.remote.search

import com.jean.cinemapp.data.mappers.toMovie
import com.jean.cinemapp.data.network.RetrofitService
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.ErrorTypes
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(private val retrofitService: RetrofitService): SearchRemoteDataSource {

    override suspend fun getSearchMovies(query: String): Resource<List<Movie>> {
        val response = retrofitService.getSearchMovies(query, 1)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                return if (body.movies.isNullOrEmpty()) {
                    Resource.Error(ErrorTypes.EMPTY_LIST)
                } else {
                    Resource.Success(body.movies.map { movieAPI ->
                        movieAPI.toMovie()
                    })
                }
            }
        } else {
            return Resource.Error(ErrorTypes.FAILED_RESPONSE)
        }
        return Resource.Error(ErrorTypes.FAILED_CONNECTION)
    }
}