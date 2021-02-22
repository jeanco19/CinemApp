package com.jean.cinemapp.utils

import android.content.Context
import com.jean.cinemapp.R
import com.jean.cinemapp.domain.model.profile.Option
import com.jean.cinemapp.utils.Constants.FIREBASE_ERROR_EMAIL_IN_USE
import com.jean.cinemapp.utils.Constants.FIREBASE_ERROR_INVALID_EMAIL
import com.jean.cinemapp.utils.Constants.FIREBASE_ERROR_USER_DISABLE
import com.jean.cinemapp.utils.Constants.FIREBASE_ERROR_USER_NOT_FOUND
import com.jean.cinemapp.utils.Constants.FIREBASE_ERROR_WEAK_PASSWORD
import com.jean.cinemapp.utils.Constants.FIREBASE_ERROR_WRONG_PASSWORD
import com.jean.cinemapp.utils.Constants.OPTION_CHANGE_LANGUAGE
import com.jean.cinemapp.utils.Constants.OPTION_CHANGE_PASSWORD
import com.jean.cinemapp.utils.Constants.OPTION_EDIT_PROFILE

class Helper {

    companion object {

        // AUTHENTICATION MODULE

        fun manageFirebaseSignUpErrors(context: Context, errorMessage: String): String {
            return when (errorMessage) {
                FIREBASE_ERROR_EMAIL_IN_USE -> {
                    context.getString(R.string.error_email_in_use)
                }
                FIREBASE_ERROR_WEAK_PASSWORD -> {
                    context.getString(R.string.error_weak_password)
                }
                FIREBASE_ERROR_INVALID_EMAIL -> {
                    context.getString(R.string.error_invalid_email)
                }
                else -> {
                    context.getString(R.string.error_sign_up)
                }
            }
        }

        fun manageFirebaseSignInErrors(context: Context, errorMessage: String): String {
            return when (errorMessage) {
                FIREBASE_ERROR_USER_NOT_FOUND -> {
                    context.getString(R.string.error_user_not_found)
                }
                FIREBASE_ERROR_INVALID_EMAIL -> {
                    context.getString(R.string.error_invalid_email)
                }
                FIREBASE_ERROR_WRONG_PASSWORD -> {
                    context.getString(R.string.error_wrong_password)
                }
                FIREBASE_ERROR_USER_DISABLE -> {
                    context.getString(R.string.error_user_disable)
                }
                else -> {
                    context.getString(R.string.error_sign_in)
                }
            }
        }

        fun manageFirebaseUpdatePasswordErrors(context: Context, errorMessage: String): String {
            return when (errorMessage) {
                FIREBASE_ERROR_WEAK_PASSWORD -> {
                    context.getString(R.string.error_weak_password)
                }
                else -> {
                    context.getString(R.string.error_changing_password)
                }
            }
        }

        // PROFILE MODULE

        fun getGeneralSettingOptions(context: Context): List<Option> {
            return listOf(
                Option(R.drawable.ic_account_box_black_24dp, context.getString(R.string.option_edit_profile), OPTION_EDIT_PROFILE),
                Option(R.drawable.ic_lock_black_24dp, context.getString(R.string.option_change_password), OPTION_CHANGE_PASSWORD),
                Option(R.drawable.ic_language_black_24dp, context.getString(R.string.option_change_language), OPTION_CHANGE_LANGUAGE)
            )
        }
    }
}