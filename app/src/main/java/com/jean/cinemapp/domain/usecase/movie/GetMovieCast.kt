package com.jean.cinemapp.domain.usecase.movie

import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.utils.Resource

interface GetMovieCast {

    suspend fun invoke(movieId: String): Resource<List<Cast>>
}