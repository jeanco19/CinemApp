package com.jean.cinemapp.domain.repository.movie

import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.utils.Resource

interface MovieDetailRepository {

    suspend fun getMovieCast(movieId: String): Resource<List<Cast>>
}