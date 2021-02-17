package com.jean.cinemapp.data.datasource.remote.auth

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.UserProfileChangeRequest
import com.jean.cinemapp.R
import com.jean.cinemapp.domain.model.auth.User
import com.jean.cinemapp.utils.ErrorTypes
import com.jean.cinemapp.utils.Helper
import com.jean.cinemapp.utils.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationDataSourceImpl @Inject constructor(@ApplicationContext private val context: Context,
                                                       private val firebaseAuth: FirebaseAuth): AuthenticationDataSource {

    override suspend fun signUp(email: String, password: String, name: String): Resource<Boolean> {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val firebaseUser = firebaseAuth.currentUser
            val profileUpdate = UserProfileChangeRequest.Builder().setDisplayName(name).build()
            firebaseUser!!.updateProfile(profileUpdate)
            Resource.Success(true, context.getString(R.string.successfully_sign_up))
        } catch (exception: FirebaseAuthException) {
            Resource.Error(ErrorTypes.FAILED_RESPONSE, message = Helper.manageFirebaseSignUpErrors(context, exception.errorCode))
        }
    }

    override suspend fun signIn(email: String, password: String): Resource<Boolean> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Resource.Success(true)
        } catch (exception: FirebaseAuthException) {
            Resource.Error(ErrorTypes.FAILED_RESPONSE, message = Helper.manageFirebaseSignInErrors(context, exception.errorCode))
        }
    }

    override suspend fun signOut(): Resource<Boolean> {
        return try {
            firebaseAuth.signOut()
            Resource.Success(true)
        } catch (exception: FirebaseAuthException) {
            Resource.Error(ErrorTypes.FAILED_RESPONSE, context.getString(R.string.error_sign_out))
        }
    }

    override suspend fun recoverPassword(email: String): Resource<Boolean> {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            Resource.Success(true, context.getString(R.string.recover_password_email_send))
        } catch (exception: FirebaseAuthException) {
            Resource.Error(ErrorTypes.FAILED_RESPONSE, context.getString(R.string.error_send_recover_password_email))
        }
    }

    override fun getUserData(): User? {
        var user: User? = null
        firebaseAuth.currentUser?.let { firebaseUser ->
           user = User(
               id = firebaseUser.uid,
               name = firebaseUser.displayName!!,
               email = firebaseUser.email!!,
               avatar = firebaseUser.photoUrl
           )
        }
        return user
    }
}