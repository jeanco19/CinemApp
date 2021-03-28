package com.jean.cinemapp.data.datasource.remote.movie

import com.jean.cinemapp.data.mappers.toCast
import com.jean.cinemapp.data.mappers.toMovie
import com.jean.cinemapp.data.network.MovieResponse
import com.jean.cinemapp.data.network.RetrofitService
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.ErrorTypes
import com.jean.cinemapp.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(private val retrofitService: RetrofitService): MovieRemoteDataSource {

    override suspend fun getRecommendedMovies(): Resource<List<Movie>> =
        handleResponse(retrofitService.getRecommendedMovies(1))

    override suspend fun getInCinemaMovies(): Resource<List<Movie>> =
        handleResponse(retrofitService.getInCinemaMovies(1))

    override suspend fun getNextReleaseMovies(): Resource<List<Movie>> =
        handleResponse(retrofitService.getNexReleaseMovies(1))

    private fun handleResponse(response: Response<MovieResponse>): Resource<List<Movie>> {
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