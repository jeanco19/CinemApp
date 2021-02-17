package com.jean.cinemapp.data.datasource.local.movie

import com.jean.cinemapp.data.database.entity.CastEntity
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.utils.Resource

interface MovieDetailLocalDataSource {

    suspend fun getMovieCast(movieId: String): Resource<List<Cast>>

    suspend fun saveCast(castEntity: CastEntity)
}