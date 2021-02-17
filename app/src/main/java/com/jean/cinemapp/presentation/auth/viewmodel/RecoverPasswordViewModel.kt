package com.jean.cinemapp.presentation.auth.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jean.cinemapp.domain.usecase.auth.RecoverPassword
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class RecoverPasswordViewModel @ViewModelInject constructor(private val recoverPassword: RecoverPassword): ViewModel() {

    private val _mRecoverPassword = MutableLiveData<Resource<Boolean>>()
    val mRecoverPassword: LiveData<Resource<Boolean>> get() = _mRecoverPassword

    fun doRecoverPassword(email: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        emit(recoverPassword.invoke(email))
    }
}