package com.jean.cinemapp.domain.usecase.favorite

import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.domain.repository.favorite.FavoriteRepository
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteMoviesImpl @Inject constructor(private val favoriteRepository: FavoriteRepository): GetFavoriteMovies {

    override fun invoke(): Flow<Resource<List<Movie>>> =
        favoriteRepository.getFavoriteMovies()
}