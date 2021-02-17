package com.jean.cinemapp.data.datasource.remote.movie

import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.utils.Resource

interface MovieDetailRemoteDataSource {

    suspend fun getMovieCast(movieId: String): Resource<List<Cast>>
}