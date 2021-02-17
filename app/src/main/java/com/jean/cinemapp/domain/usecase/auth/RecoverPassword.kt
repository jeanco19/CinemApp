package com.jean.cinemapp.domain.usecase.auth

import com.jean.cinemapp.utils.Resource

interface RecoverPassword {

    suspend fun invoke(email: String): Resource<Boolean>
}