package com.jean.cinemapp.domain.model.movie

data class Cast(
    val id: Int,
    val name: String,
    val profilePath: String?,
    val movieId: Int
)