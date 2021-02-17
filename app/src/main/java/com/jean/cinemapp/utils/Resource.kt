package com.jean.cinemapp.utils

enum class ErrorTypes { EMPTY_LIST, FAILED_RESPONSE, FAILED_CONNECTION, WITHOUT_CONNECTION }

sealed class Resource<T> (val data: T? = null, val message: String? = null, val errorType: ErrorTypes? = null) {

    class Success<T>(data: T, message: String? = null): Resource<T>(data, message)
    class Error<T>(type: ErrorTypes, message: String? = null, data: T? = null): Resource<T>(data, message, type)
    class Loading<T>: Resource<T>()
}