package com.jean.cinemapp.domain.usecase.auth

import com.jean.cinemapp.utils.Resource

interface SignUp {

    suspend fun invoke(email: String, password: String, name: String): Resource<Boolean>
}