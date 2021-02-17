package com.jean.cinemapp.domain.usecase.favorite

import com.jean.cinemapp.domain.repository.favorite.FavoriteRepository
import javax.inject.Inject

class DeleteAllFavoritesImpl @Inject constructor(private val favoriteRepository: FavoriteRepository): DeleteAllFavorites {

    override suspend fun invoke() {
        favoriteRepository.deleteAllFavorites()
    }
}