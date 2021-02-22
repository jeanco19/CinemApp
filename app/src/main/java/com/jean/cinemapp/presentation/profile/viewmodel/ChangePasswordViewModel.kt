package com.jean.cinemapp.presentation.profile.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jean.cinemapp.domain.usecase.auth.ChangePassword
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class ChangePasswordViewModel @ViewModelInject constructor(private val changePassword: ChangePassword): ViewModel() {

    private val _mChangePassword = MutableLiveData<Resource<Boolean>>()
    val mChangePassword: LiveData<Resource<Boolean>> get() = _mChangePassword

    fun doChangePassword(newPassword: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        emit(changePassword.invoke(newPassword))
    }
}