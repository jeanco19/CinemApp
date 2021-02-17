package com.jean.cinemapp.domain.usecase.auth

import com.jean.cinemapp.domain.repository.auth.AuthenticationRepository
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class RecoverPasswordImpl @Inject constructor(private val authenticationRepository: AuthenticationRepository): RecoverPassword {

    override suspend fun invoke(email: String): Resource<Boolean> =
        authenticationRepository.recoverPassword(email)
}