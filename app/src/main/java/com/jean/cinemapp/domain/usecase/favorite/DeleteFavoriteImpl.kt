package com.jean.cinemapp.domain.usecase.favorite

import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.domain.repository.favorite.FavoriteRepository
import javax.inject.Inject

class DeleteFavoriteImpl @Inject constructor(private val favoriteRepository: FavoriteRepository): DeleteFavorite {

    override suspend fun invoke(movie: Movie) {
        favoriteRepository.deleteFavorite(movie)
    }
}