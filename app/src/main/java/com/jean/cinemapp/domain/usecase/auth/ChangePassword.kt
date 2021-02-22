package com.jean.cinemapp.domain.usecase.auth

import com.jean.cinemapp.utils.Resource

interface ChangePassword {

    suspend fun invoke(newPassword: String): Resource<Boolean>
}