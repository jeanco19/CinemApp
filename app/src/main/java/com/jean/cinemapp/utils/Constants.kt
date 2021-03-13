package com.jean.cinemapp.utils

object Constants {

    // WEB SERVICE DATA

    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    const val API_KEY = "121237d7516cc37b2c6ea837480e8bcf"

    // ENDPOINTS

    const val ENDPOINT_POPULAR_MOVIES = "movie/popular"
    const val ENDPOINT_IN_CINEMA_MOVIES = "movie/now_playing"
    const val ENDPOINT_UPCOMING_MOVIES = "movie/upcoming"
    const val ENDPOINT_MOVIE_DETAIL = "movie/{movie_id}/credits"
    const val ENDPOINT_SEARCH_MOVIES = "search/movie"

    // INTERCEPTOR DATA

    const val URL_PARAM_API_KEY = "api_key"
    const val URL_PARAM_LANGUAGE = "language"
    const val LANGUAGE = "es-ES"
    const val URL_HEADER_CONTENT_TYPE = "Content-Type"
    const val URL_HEADER_ACCEPT = "Accept"
    const val CONTENT_TYPE = "application/json"

    // WEB SERVICE SERIALIZABLE NAME FIELDS

    const val API_RESULTS = "results"
    const val API_ID = "id"
    const val API_NAME = "name"
    const val API_TITLE = "title"
    const val API_PROFILE_PATH = "profile_path"
    const val API_POSTER_PATH = "poster_path"
    const val API_OVERVIEW = "overview"
    const val API_RELEASE_DATE = "release_date"
    const val API_VOTE_AVERAGE = "vote_average"
    const val API_POPULARITY = "popularity"
    const val API_MOVIE_ID = "movie_id"
    const val API_PAGE = "page"
    const val API_QUERY = "query"

    // FIREBASE AUTHENTICATION ERRORS

    const val FIREBASE_ERROR_EMAIL_IN_USE = "ERROR_EMAIL_ALREADY_IN_USE"
    const val FIREBASE_ERROR_WEAK_PASSWORD = "ERROR_WEAK_PASSWORD"
    const val FIREBASE_ERROR_WRONG_PASSWORD = "ERROR_WRONG_PASSWORD"
    const val FIREBASE_ERROR_INVALID_EMAIL = "ERROR_INVALID_EMAIL"
    const val FIREBASE_ERROR_USER_NOT_FOUND = "ERROR_USER_NOT_FOUND"
    const val FIREBASE_ERROR_USER_DISABLE = "ERROR_USER_DISABLED"
    const val FIREBASE_ERROR_REQUIRE_RECENT_LOGIN = "ERROR_REQUIRES_RECENT_LOGIN"
    const val FIREBASE_ERROR_USER_MISMATCH = "ERROR_USER_MISMATCH"
    const val FIREBASE_ERROR_INVALID_CREDENTIAL = "ERROR_INVALID_CREDENTIAL"

    // SETTING OPTIONS

    const val OPTION_EDIT_PROFILE = "edit_profile"
    const val OPTION_CHANGE_PASSWORD = "change_password"
    const val OPTION_CHANGE_LANGUAGE = "change_language"

    // NAVIGATION

    const val NAVIGATION_FROM_HOME = "nav_from_home"
    const val NAVIGATION_FROM_SEARCH = "nav_from_search"
    const val NAVIGATION_FROM_FAVORITE = "nav_from_favorite"

    // MOVIE TYPES

    const val MOVIE_RECOMMENDED = "recommended"
    const val MOVIE_IN_CINEMA = "ic_cinema"
    const val MOVIE_NEXT_RELEASE = "next_release"

    // DATABASE DATA

    const val DATABASE_NAME = "cinema_database"
    const val TABLE_MOVIE = "table_movie"
    const val TABLE_CAST = "table_cast"
    const val TABLE_FAVORITE = "table_favorite"

    // DATABASE COLUMN INFO FIELDS

    const val DB_DESCRIPTION = "description"
    const val DB_IMAGE = "image"
    const val DB_RELEASE_DATE = "release_date"
    const val DB_VOTE_AVERAGE = "vote_average"
    const val DB_MOVIE_ID = "movie_id"

    // INTERNET INFO

    const val INTERNET_HOST_NAME = "8.8.8.8"
}