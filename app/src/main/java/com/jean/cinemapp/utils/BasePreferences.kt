package com.jean.cinemapp.utils

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BasePreferences @Inject constructor(@ApplicationContext context: Context) {

    private val mPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun getString(key: String): String {
        return mPreferences.getString(key, "")!!
    }

    fun putString(key: String, value: String) {
        mPreferences.edit().putString(key, value).apply()
    }

    fun delete(key: String) {
        mPreferences.edit().remove(key).apply()
    }
}