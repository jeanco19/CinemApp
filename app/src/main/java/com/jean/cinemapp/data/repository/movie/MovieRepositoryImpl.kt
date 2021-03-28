package com.jean.cinemapp.data.repository.movie

import com.jean.cinemapp.data.datasource.local.movie.MovieLocalDataSource
import com.jean.cinemapp.data.datasource.remote.movie.MovieRemoteDataSource
import com.jean.cinemapp.data.mappers.toCastEntity
import com.jean.cinemapp.data.mappers.toMovieEntity
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.domain.repository.movie.MovieRepository
import com.jean.cinemapp.utils.Connectivity
import com.jean.cinemapp.utils.Constants.MOVIE_IN_CINEMA
import com.jean.cinemapp.utils.Constants.MOVIE_NEXT_RELEASE
import com.jean.cinemapp.utils.Constants.MOVIE_RECOMMENDED
import com.jean.cinemapp.utils.ErrorTypes
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource,
                                              private val movieLocalDataSource: MovieLocalDataSource): MovieRepository {

    override suspend fun getRecommendedMovies(): Resource<List<Movie>> {
        if (Connectivity.isNetworkAvailable()) {
            val remoteDataSource = movieRemoteDataSource.getRecommendedMovies()
            if (remoteDataSource.data.isNullOrEmpty()) {
                return remoteDataSource
            } else {
                remoteDataSource.data.forEach { movie ->
                    movieLocalDataSource.saveMovie(movie.toMovieEntity(MOVIE_RECOMMENDED))
                }
                return remoteDataSource
            }
        } else {
            return Resource.Error(ErrorTypes.WITHOUT_CONNECTION, data = movieLocalDataSource.getRecommendedMovies().data)
        }
    }

    override suspend fun getInCinemaMovies(): Resource<List<Movie>> {
        if (Connectivity.isNetworkAvailable()) {
            val remoteDataSource = movieRemoteDataSource.getInCinemaMovies()
            if (remoteDataSource.data.isNullOrEmpty()) {
                return remoteDataSource
            } else {
                remoteDataSource.data.forEach { movie ->
                    movieLocalDataSource.saveMovie(movie.toMovieEntity(MOVIE_IN_CINEMA))
                }
                return remoteDataSource
            }
        } else {
            return Resource.Error(ErrorTypes.WITHOUT_CONNECTION, data = movieLocalDataSource.getInCinemaMovies().data)
        }
    }

    override suspend fun getNextReleaseMovies(): Resource<List<Movie>> {
        if (Connectivity.isNetworkAvailable()) {
            val remoteDataSource = movieRemoteDataSource.getNextReleaseMovies()
            if (remoteDataSource.data.isNullOrEmpty()) {
                return remoteDataSource
            } else {
                remoteDataSource.data.forEach { movie ->
                    movieLocalDataSource.saveMovie(movie.toMovieEntity(MOVIE_NEXT_RELEASE))
                }
                return remoteDataSource
            }
        } else {
            return Resource.Error(ErrorTypes.WITHOUT_CONNECTION, data = movieLocalDataSource.getNextReleaseMovies().data)
        }
    }

    override suspend fun getMovieCast(movieId: String): Resource<List<Cast>> {
        if (Connectivity.isNetworkAvailable()) {
            val remoteDataSource = movieRemoteDataSource.getMovieCast(movieId)
            if (remoteDataSource.data.isNullOrEmpty()) {
                return remoteDataSource
            } else {
                remoteDataSource.data.forEach { cast ->
                    movieLocalDataSource.saveCast(cast.toCastEntity())
                }
                return remoteDataSource
            }
        } else {
            return Resource.Error(ErrorTypes.WITHOUT_CONNECTION, data = movieLocalDataSource.getMovieCast(movieId).data)
        }
    }
}