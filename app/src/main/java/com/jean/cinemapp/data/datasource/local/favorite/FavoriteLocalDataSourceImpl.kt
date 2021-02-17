package com.jean.cinemapp.data.datasource.local.favorite

import com.jean.cinemapp.data.database.CinemaDao
import com.jean.cinemapp.data.database.entity.FavoriteEntity
import com.jean.cinemapp.data.mappers.toMovie
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.ErrorTypes
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FavoriteLocalDataSourceImpl @Inject constructor(private val cinemaDao: CinemaDao): FavoriteLocalDataSource {

    override fun getFavoriteMovies(): Flow<Resource<List<Movie>>> = flow {
        cinemaDao.getFavoriteMovies().collect { favorites ->
            if (favorites.isNullOrEmpty()) {
                emit(Resource.Error<List<Movie>>(ErrorTypes.EMPTY_LIST))
            } else {
                emit(Resource.Success(favorites.map { favoriteEntity ->
                    favoriteEntity.toMovie()
                }))
            }
        }
    }

    override suspend fun saveFavorite(favoriteEntity: FavoriteEntity) {
        cinemaDao.saveFavorite(favoriteEntity)
    }

    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity) {
        cinemaDao.deleteFavoriteMovie(favoriteEntity)
    }

    override suspend fun deleteAllFavorites() {
        cinemaDao.deleteAllFavorites()
    }
}