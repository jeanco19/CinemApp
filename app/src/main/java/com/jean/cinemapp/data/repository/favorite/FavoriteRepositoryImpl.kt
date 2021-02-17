package com.jean.cinemapp.data.repository.favorite

import com.jean.cinemapp.data.datasource.local.favorite.FavoriteLocalDataSource
import com.jean.cinemapp.data.mappers.toFavoriteEntity
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.domain.repository.favorite.FavoriteRepository
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val favoriteLocalDataSource: FavoriteLocalDataSource): FavoriteRepository {

    override fun getFavoriteMovies(): Flow<Resource<List<Movie>>> =
        favoriteLocalDataSource.getFavoriteMovies()

    override suspend fun saveFavorite(movie: Movie) {
        favoriteLocalDataSource.saveFavorite(movie.toFavoriteEntity())
    }

    override suspend fun deleteFavorite(movie: Movie) {
        favoriteLocalDataSource.deleteFavorite(movie.toFavoriteEntity())
    }

    override suspend fun deleteAllFavorites() {
        favoriteLocalDataSource.deleteAllFavorites()
    }
}