package com.jean.cinemapp.domain.usecase.movie

import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.domain.repository.movie.MovieDetailRepository
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class GetMovieCastImpl @Inject constructor(private val movieDetailRepository: MovieDetailRepository): GetMovieCast {

    override suspend fun invoke(movieId: String): Resource<List<Cast>> = movieDetailRepository.getMovieCast(movieId)
}