package com.jean.cinemapp.domain.repository.favorite

import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    fun getFavoriteMovies(): Flow<Resource<List<Movie>>>

    suspend fun saveFavorite(movie: Movie)

    suspend fun deleteFavorite(movie: Movie)

    suspend fun deleteAllFavorites()
}