package com.jean.cinemapp.presentation.auth.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jean.cinemapp.domain.usecase.auth.SignUp
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class SignUpViewModel @ViewModelInject constructor(private val signUp: SignUp): ViewModel() {

    private val _mSignUp = MutableLiveData<Resource<Boolean>>()
    val mSignUp: LiveData<Resource<Boolean>> get() = _mSignUp

    fun doSignUp(email: String, password: String, name: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        emit(signUp.invoke(email, password, name))
    }
}