package com.jean.cinemapp.domain.usecase.auth

import com.jean.cinemapp.utils.Resource

interface SignOut {

    suspend fun invoke(): Resource<Boolean>
}