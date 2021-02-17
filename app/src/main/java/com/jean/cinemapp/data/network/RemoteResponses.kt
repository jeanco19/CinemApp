package com.jean.cinemapp.data.network

import com.google.gson.annotations.SerializedName
import com.jean.cinemapp.utils.Constants.API_RESULTS

data class MovieResponse(
    @SerializedName(API_RESULTS)
    val movies: List<MovieAPI>
)

data class CastResponse(
    val cast: List<CastAPI>
)