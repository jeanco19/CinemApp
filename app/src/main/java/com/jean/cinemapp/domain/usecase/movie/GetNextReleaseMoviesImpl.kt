package com.jean.cinemapp.domain.usecase.movie

import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.domain.repository.movie.MovieRepository
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class GetNextReleaseMoviesImpl @Inject constructor(private val movieRepository: MovieRepository): GetNextReleaseMovies {

    override suspend fun invoke(): Resource<List<Movie>> = movieRepository.getNextReleaseMovies()
}