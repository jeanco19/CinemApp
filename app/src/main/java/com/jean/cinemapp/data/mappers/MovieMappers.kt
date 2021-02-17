package com.jean.cinemapp.data.mappers

import com.jean.cinemapp.data.database.entity.CastEntity
import com.jean.cinemapp.data.database.entity.FavoriteEntity
import com.jean.cinemapp.data.database.entity.MovieEntity
import com.jean.cinemapp.data.network.CastAPI
import com.jean.cinemapp.data.network.MovieAPI
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.domain.model.movie.Movie

fun MovieAPI.toMovie() = Movie(
    id,
    overview,
    posterPath,
    releaseDate,
    title,
    voteAverage,
    popularity
)

fun MovieEntity.toMovie() = Movie(
    id,
    overview,
    posterPath,
    releaseDate,
    title,
    voteAverage,
    popularity
)

fun Movie.toMovieEntity(type: String) = MovieEntity(
    id,
    overview,
    posterPath,
    releaseDate,
    title,
    voteAverage,
    popularity,
    type = type
)

fun CastAPI.toCast(movieId: Int) = Cast(
    id,
    name,
    profilePath,
    movieId = movieId,
)

fun CastEntity.toCast() = Cast(
    id,
    name,
    profilePath,
    movieId
)

fun Cast.toCastEntity() = CastEntity(
    id,
    name,
    profilePath,
    movieId
)

fun FavoriteEntity.toMovie() = Movie(
    id,
    overview,
    posterPath,
    releaseDate,
    title,
    voteAverage,
    popularity
)

fun Movie.toFavoriteEntity() = FavoriteEntity(
    id,
    overview,
    posterPath,
    releaseDate,
    title,
    voteAverage,
    popularity
)