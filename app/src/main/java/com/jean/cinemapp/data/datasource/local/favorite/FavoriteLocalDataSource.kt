package com.jean.cinemapp.data.datasource.local.favorite

import com.jean.cinemapp.data.database.entity.FavoriteEntity
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FavoriteLocalDataSource {

    fun getFavoriteMovies(): Flow<Resource<List<Movie>>>

    suspend fun saveFavorite(favoriteEntity: FavoriteEntity)

    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity)

    suspend fun deleteAllFavorites()
}