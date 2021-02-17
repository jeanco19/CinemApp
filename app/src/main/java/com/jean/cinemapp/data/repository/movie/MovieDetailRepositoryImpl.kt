package com.jean.cinemapp.data.repository.movie

import com.jean.cinemapp.data.datasource.local.movie.MovieDetailLocalDataSource
import com.jean.cinemapp.data.datasource.remote.movie.MovieDetailRemoteDataSource
import com.jean.cinemapp.data.mappers.toCastEntity
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.domain.repository.movie.MovieDetailRepository
import com.jean.cinemapp.utils.Connectivity
import com.jean.cinemapp.utils.ErrorTypes
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(private val movieDetailRemoteDataSource: MovieDetailRemoteDataSource,
                                                    private val movieDetailLocalDataSource: MovieDetailLocalDataSource): MovieDetailRepository {

    override suspend fun getMovieCast(movieId: String): Resource<List<Cast>> {
        if (Connectivity.isNetworkAvailable()) {
            val remoteDataSource = movieDetailRemoteDataSource.getMovieCast(movieId)
            if (remoteDataSource.data.isNullOrEmpty()) {
                return remoteDataSource
            } else {
                remoteDataSource.data.forEach { cast ->
                    movieDetailLocalDataSource.saveCast(cast.toCastEntity())
                }
                return remoteDataSource
            }
        } else {
            return Resource.Error(ErrorTypes.WITHOUT_CONNECTION, data = movieDetailLocalDataSource.getMovieCast(movieId).data)
        }
    }
}