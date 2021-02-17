package com.jean.cinemapp.domain.usecase.favorite

import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetFavoriteMovies {

    fun invoke(): Flow<Resource<List<Movie>>>
}