package com.jean.cinemapp.presentation.profile.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.jean.cinemapp.domain.model.auth.User
import com.jean.cinemapp.domain.usecase.auth.GetUserData
import com.jean.cinemapp.domain.usecase.auth.SignOut
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.Dispatchers

class ProfileViewModel @ViewModelInject constructor(private val signOut: SignOut,
                                                    private val getUserData: GetUserData): ViewModel() {

    private val _mSignOut = MutableLiveData<Resource<Boolean>>()
    val mSignOut: LiveData<Resource<Boolean>> get() = _mSignOut

    fun doSignOut() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        emit(signOut.invoke())
    }

    fun getUserData(): User? = getUserData.invoke()
}