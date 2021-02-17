package com.jean.cinemapp.data.datasource.local.movie

import com.jean.cinemapp.data.database.entity.CastEntity
import com.jean.cinemapp.data.database.CinemaDao
import com.jean.cinemapp.data.mappers.toCast
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.utils.ErrorTypes
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class MovieDetailLocalDataSourceImpl @Inject constructor(private val cinemaDao: CinemaDao): MovieDetailLocalDataSource {

    override suspend fun getMovieCast(movieId: String): Resource<List<Cast>> {
        val castList = cinemaDao.getMovieCast(movieId.toInt())
        return Resource.Error(ErrorTypes.WITHOUT_CONNECTION, data = castList.map { castEntity ->
            castEntity.toCast()
        })
    }

    override suspend fun saveCast(castEntity: CastEntity) {
        cinemaDao.saveCast(castEntity)
    }
}