package com.jean.cinemapp.domain.usecase.auth

import com.jean.cinemapp.domain.model.auth.User
import com.jean.cinemapp.domain.repository.auth.AuthenticationRepository
import javax.inject.Inject

class GetUserDataImpl @Inject constructor(private val authenticationRepository: AuthenticationRepository): GetUserData {

    override fun invoke(): User? =
        authenticationRepository.getUserData()
}