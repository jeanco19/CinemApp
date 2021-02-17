package com.jean.cinemapp.domain.usecase.favorite

import com.jean.cinemapp.domain.model.movie.Movie

interface DeleteFavorite {

    suspend fun invoke(movie: Movie)
}