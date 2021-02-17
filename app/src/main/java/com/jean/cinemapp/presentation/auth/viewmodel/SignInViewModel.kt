package com.jean.cinemapp.presentation.auth.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jean.cinemapp.domain.usecase.auth.SignIn
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class SignInViewModel @ViewModelInject constructor(private val signIn: SignIn): ViewModel() {

    private val _mSignIn = MutableLiveData<Resource<Boolean>>()
    val mSignIn: LiveData<Resource<Boolean>> get() = _mSignIn

    fun doSignIn(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        emit(signIn.invoke(email, password))
    }
}