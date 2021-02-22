package com.jean.cinemapp.domain.usecase.auth

import com.jean.cinemapp.domain.repository.auth.AuthenticationRepository
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class ChangePasswordImpl @Inject constructor(private val authenticationRepository: AuthenticationRepository): ChangePassword {

    override suspend fun invoke(newPassword: String): Resource<Boolean> =
        authenticationRepository.changePassword(newPassword)
}