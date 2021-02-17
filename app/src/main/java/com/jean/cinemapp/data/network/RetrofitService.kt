package com.jean.cinemapp.data.network

import com.jean.cinemapp.utils.Constants.API_MOVIE_ID
import com.jean.cinemapp.utils.Constants.API_PAGE
import com.jean.cinemapp.utils.Constants.API_QUERY
import com.jean.cinemapp.utils.Constants.ENDPOINT_IN_CINEMA_MOVIES
import com.jean.cinemapp.utils.Constants.ENDPOINT_MOVIE_DETAIL
import com.jean.cinemapp.utils.Constants.ENDPOINT_POPULAR_MOVIES
import com.jean.cinemapp.utils.Constants.ENDPOINT_SEARCH_MOVIES
import com.jean.cinemapp.utils.Constants.ENDPOINT_UPCOMING_MOVIES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    // MOVIE MODULE

    @GET(ENDPOINT_POPULAR_MOVIES)
    suspend fun getRecommendedMovies(@Query(API_PAGE) page: Int): Response<MovieResponse>

    @GET(ENDPOINT_IN_CINEMA_MOVIES)
    suspend fun getInCinemaMovies(@Query(API_PAGE) page: Int): Response<MovieResponse>

    @GET(ENDPOINT_UPCOMING_MOVIES)
    suspend fun getNexReleaseMovies(@Query(API_PAGE) page: Int): Response<MovieResponse>

    @GET(ENDPOINT_MOVIE_DETAIL)
    suspend fun getMovieCast(@Path(API_MOVIE_ID) movieId: String): Response<CastResponse>

    // SEARCH MODULE

    @GET(ENDPOINT_SEARCH_MOVIES)
    suspend fun getSearchMovies(@Query(API_QUERY) query: String, @Query(API_PAGE) page: Int): Response<MovieResponse>
}