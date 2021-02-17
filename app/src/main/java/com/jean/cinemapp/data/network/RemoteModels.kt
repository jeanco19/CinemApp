package com.jean.cinemapp.data.network

import com.google.gson.annotations.SerializedName
import com.jean.cinemapp.utils.Constants.API_ID
import com.jean.cinemapp.utils.Constants.API_NAME
import com.jean.cinemapp.utils.Constants.API_OVERVIEW
import com.jean.cinemapp.utils.Constants.API_POPULARITY
import com.jean.cinemapp.utils.Constants.API_POSTER_PATH
import com.jean.cinemapp.utils.Constants.API_PROFILE_PATH
import com.jean.cinemapp.utils.Constants.API_RELEASE_DATE
import com.jean.cinemapp.utils.Constants.API_TITLE
import com.jean.cinemapp.utils.Constants.API_VOTE_AVERAGE

data class MovieAPI(
    @SerializedName(API_ID) val id: Int,
    @SerializedName(API_OVERVIEW) val overview: String,
    @SerializedName(API_POSTER_PATH) val posterPath: String,
    @SerializedName(API_RELEASE_DATE) val releaseDate: String,
    @SerializedName(API_TITLE) val title: String,
    @SerializedName(API_VOTE_AVERAGE) val voteAverage: Float,
    @SerializedName(API_POPULARITY) val popularity: Float
)

data class CastAPI(
    @SerializedName(API_ID) val id: Int,
    @SerializedName(API_NAME) val name: String,
    @SerializedName(API_PROFILE_PATH) val profilePath: String?
)