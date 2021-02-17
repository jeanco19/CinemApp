package com.jean.cinemapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.jean.cinemapp.data.network.RetrofitInterceptor
import com.jean.cinemapp.data.network.RetrofitService
import com.jean.cinemapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    // RETROFIT NETWORK
    
    @Singleton
    @Provides
    fun provideBaseUrl(): String = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(retrofitInterceptor: RetrofitInterceptor): OkHttpClient =
        with(OkHttpClient.Builder()) {
            addInterceptor(retrofitInterceptor)
            build()
        }

    @Singleton
    @Provides
    fun provideRetrofitService(url: String, okHttpClient: OkHttpClient): RetrofitService =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RetrofitService::class.java)

    // FIREBASE NETWORK

    @Singleton
    @Provides
    fun provideFirebase(): Firebase = Firebase

    @Singleton
    @Provides
    fun provideFirebaseAuth(firebase: Firebase): FirebaseAuth = firebase.auth
}