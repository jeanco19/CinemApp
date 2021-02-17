package com.jean.cinemapp.domain.usecase.auth

import com.jean.cinemapp.domain.repository.auth.AuthenticationRepository
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class SignUpImpl @Inject constructor(private val authenticationRepository: AuthenticationRepository): SignUp {

    override suspend fun invoke(email: String, password: String, name: String): Resource<Boolean> =
        authenticationRepository.signUp(email, password, name)
}