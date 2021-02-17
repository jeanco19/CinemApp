package com.jean.cinemapp.domain.usecase.auth

import com.jean.cinemapp.domain.model.auth.User

interface GetUserData {

    fun invoke(): User?
}