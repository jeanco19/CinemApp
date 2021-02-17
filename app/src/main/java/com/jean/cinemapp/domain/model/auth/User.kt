package com.jean.cinemapp.domain.model.auth

import android.net.Uri

data class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var avatar: Uri? = null
)