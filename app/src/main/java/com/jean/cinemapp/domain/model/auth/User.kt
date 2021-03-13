package com.jean.cinemapp.domain.model.auth

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var avatar: Uri? = null
): Parcelable