package com.jean.cinemapp.domain.usecase.auth

import com.jean.cinemapp.domain.repository.auth.AuthenticationRepository
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class SignOutImpl @Inject constructor(private val authenticationRepository: AuthenticationRepository): SignOut {

    override suspend fun invoke(): Resource<Boolean> =
        authenticationRepository.signOut()
}