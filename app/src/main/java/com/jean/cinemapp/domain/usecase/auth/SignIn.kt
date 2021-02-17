package com.jean.cinemapp.domain.usecase.auth

import com.jean.cinemapp.utils.Resource

interface SignIn {

    suspend fun invoke(email: String, password: String): Resource<Boolean>
}