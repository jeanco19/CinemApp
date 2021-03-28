package com.jean.cinemapp.domain.usecase.movie

import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.domain.repository.movie.MovieRepository
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class GetMovieCastImpl @Inject constructor(private val movieRepository: MovieRepository): GetMovieCast {

    override suspend fun invoke(movieId: String): Resource<List<Cast>> = movieRepository.getMovieCast(movieId)
}