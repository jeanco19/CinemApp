package com.jean.cinemapp.data.repository.auth

import com.jean.cinemapp.data.datasource.remote.auth.AuthenticationDataSource
import com.jean.cinemapp.domain.model.auth.User
import com.jean.cinemapp.domain.repository.auth.AuthenticationRepository
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val authenticationDataSource: AuthenticationDataSource): AuthenticationRepository {

    override suspend fun signUp(email: String, password: String, name: String): Resource<Boolean> =
        authenticationDataSource.signUp(email, password, name)

    override suspend fun signIn(email: String, password: String): Resource<Boolean> =
        authenticationDataSource.signIn(email, password)

    override suspend fun signOut(): Resource<Boolean> =
        authenticationDataSource.signOut()

    override suspend fun recoverPassword(email: String): Resource<Boolean> =
        authenticationDataSource.recoverPassword(email)

    override fun getUserData(): User? =
        authenticationDataSource.getUserData()
}