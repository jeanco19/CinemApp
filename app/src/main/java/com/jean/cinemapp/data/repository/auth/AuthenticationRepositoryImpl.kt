package com.jean.cinemapp.data.repository.auth

import com.jean.cinemapp.data.datasource.remote.auth.AuthenticationDataSource
import com.jean.cinemapp.domain.model.auth.User
import com.jean.cinemapp.domain.repository.auth.AuthenticationRepository
import com.jean.cinemapp.utils.Connectivity
import com.jean.cinemapp.utils.ErrorTypes
import com.jean.cinemapp.utils.Resource
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val authenticationDataSource: AuthenticationDataSource): AuthenticationRepository {

    override suspend fun signUp(email: String, password: String, name: String): Resource<Boolean> {
        return if (Connectivity.isNetworkAvailable()) {
            authenticationDataSource.signUp(email, password, name)
        } else {
            Resource.Error(ErrorTypes.WITHOUT_CONNECTION)
        }
    }

    override suspend fun signIn(email: String, password: String): Resource<Boolean> {
        return if (Connectivity.isNetworkAvailable()) {
            authenticationDataSource.signIn(email, password)
        } else {
            Resource.Error(ErrorTypes.WITHOUT_CONNECTION)
        }
    }

    override suspend fun signOut(): Resource<Boolean> {
        return if (Connectivity.isNetworkAvailable()) {
            authenticationDataSource.signOut()
        } else {
            Resource.Error(ErrorTypes.WITHOUT_CONNECTION)
        }
    }

    override suspend fun recoverPassword(email: String): Resource<Boolean> {
        return if (Connectivity.isNetworkAvailable()) {
            authenticationDataSource.recoverPassword(email)
        } else {
            Resource.Error(ErrorTypes.WITHOUT_CONNECTION)
        }
    }

    override suspend fun changePassword(newPassword: String): Resource<Boolean> {
        return if (Connectivity.isNetworkAvailable()) {
            authenticationDataSource.changePassword(newPassword)
        } else {
            Resource.Error(ErrorTypes.WITHOUT_CONNECTION)
        }
    }

    override fun getUserData(): User? =
        authenticationDataSource.getUserData()
}