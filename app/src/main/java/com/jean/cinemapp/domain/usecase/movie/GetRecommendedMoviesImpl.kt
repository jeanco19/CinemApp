package com.jean.cinemapp.domain.usecase.movie

import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.domain.repository.movie.MovieRepository
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class GetRecommendedMoviesImpl @Inject constructor(private val movieRepository: MovieRepository): GetRecommendedMovies {

    override suspend fun invoke(): Resource<List<Movie>> = movieRepository.getRecommendedMovies()
}