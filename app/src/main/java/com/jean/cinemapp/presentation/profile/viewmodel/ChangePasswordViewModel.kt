package com.jean.cinemapp.presentation.profile.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jean.cinemapp.domain.model.auth.User
import com.jean.cinemapp.domain.usecase.auth.ChangePassword
import com.jean.cinemapp.domain.usecase.auth.GetUserData
import com.jean.cinemapp.domain.usecase.auth.ReAuthenticate
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class ChangePasswordViewModel @ViewModelInject constructor(private val changePassword: ChangePassword,
                                                           private val reAuthenticate: ReAuthenticate,
                                                           private val getUserData: GetUserData): ViewModel() {

    private val _mChangePassword = MutableLiveData<Resource<Boolean>>()
    val mChangePassword: LiveData<Resource<Boolean>> get() = _mChangePassword
    private val _mReAuthenticate = MutableLiveData<Resource<Boolean>>()
    val mReAuthenticate: LiveData<Resource<Boolean>> get() = _mReAuthenticate

    fun doChangePassword(newPassword: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        emit(changePassword.invoke(newPassword))
    }

    fun doReAuthenticate(email: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        emit(reAuthenticate.invoke(email, password))
    }

    fun getUserData(): User? = getUserData.invoke()
}