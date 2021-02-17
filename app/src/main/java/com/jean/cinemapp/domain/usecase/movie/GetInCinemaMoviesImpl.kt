package com.jean.cinemapp.domain.usecase.movie

import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.domain.repository.movie.MovieRepository
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class GetInCinemaMoviesImpl @Inject constructor(private val movieRepository: MovieRepository): GetInCinemaMovies {

    override suspend fun invoke(): Resource<List<Movie>> = movieRepository.getInCinemaMovies()
}