package com.jean.cinemapp.data.datasource.remote.auth

import com.jean.cinemapp.domain.model.auth.User
import com.jean.cinemapp.utils.Resource

interface AuthenticationDataSource {

    suspend fun signUp(email: String, password: String, name: String): Resource<Boolean>

    suspend fun signIn(email: String, password: String): Resource<Boolean>

    suspend fun signOut(): Resource<Boolean>

    suspend fun recoverPassword(email: String): Resource<Boolean>

    fun getUserData(): User?
}