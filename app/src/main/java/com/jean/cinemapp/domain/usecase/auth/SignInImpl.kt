package com.jean.cinemapp.domain.usecase.auth

import com.jean.cinemapp.domain.repository.auth.AuthenticationRepository
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class SignInImpl @Inject constructor(private val authenticationRepository: AuthenticationRepository): SignIn {

    override suspend fun invoke(email: String, password: String): Resource<Boolean> =
        authenticationRepository.signIn(email, password)
}