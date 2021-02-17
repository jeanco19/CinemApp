package com.jean.cinemapp.data.datasource.remote.movie

import com.jean.cinemapp.data.mappers.toCast
import com.jean.cinemapp.data.network.RetrofitService
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.utils.ErrorTypes
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class MovieDetailRemoteDataSourceImpl @Inject constructor(private val retrofitService: RetrofitService): MovieDetailRemoteDataSource {

    override suspend fun getMovieCast(movieId: String): Resource<List<Cast>> {
        val response = retrofitService.getMovieCast(movieId)
        if (response.isSuccessful) {
            response.body()?.let { body ->
                return if (body.cast.isNullOrEmpty()) {
                    Resource.Error(ErrorTypes.EMPTY_LIST)
                } else {
                    Resource.Success(body.cast.map { castAPI ->
                        castAPI.toCast(movieId.toInt())
                    })
                }
            }
        } else {
            return Resource.Error(ErrorTypes.FAILED_RESPONSE)
        }
        return Resource.Error(ErrorTypes.FAILED_CONNECTION)
    }
}