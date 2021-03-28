package com.jean.cinemapp.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.jean.cinemapp.data.database.CinemaDao
import com.jean.cinemapp.data.datasource.local.favorite.FavoriteLocalDataSource
import com.jean.cinemapp.data.datasource.local.favorite.FavoriteLocalDataSourceImpl
import com.jean.cinemapp.data.datasource.local.movie.MovieLocalDataSource
import com.jean.cinemapp.data.datasource.local.movie.MovieLocalDataSourceImpl
import com.jean.cinemapp.data.datasource.remote.auth.AuthenticationDataSource
import com.jean.cinemapp.data.datasource.remote.auth.AuthenticationDataSourceImpl
import com.jean.cinemapp.data.datasource.remote.movie.MovieRemoteDataSource
import com.jean.cinemapp.data.datasource.remote.movie.MovieRemoteDataSourceImpl
import com.jean.cinemapp.data.datasource.remote.search.SearchRemoteDataSource
import com.jean.cinemapp.data.datasource.remote.search.SearchRemoteDataSourceImpl
import com.jean.cinemapp.data.network.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {

    // AUTHENTICATION MODULE

    @Singleton
    @Provides
    fun provideAuthenticationDataSource(@ApplicationContext context: Context, firebaseAuth: FirebaseAuth): AuthenticationDataSource =
        AuthenticationDataSourceImpl(context, firebaseAuth)

    // MOVIE MODULE

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(retrofitService: RetrofitService): MovieRemoteDataSource =
        MovieRemoteDataSourceImpl(retrofitService)

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(cinemaDao: CinemaDao): MovieLocalDataSource =
        MovieLocalDataSourceImpl(cinemaDao)

    // FAVORITE MODULE

    @Singleton
    @Provides
    fun provideFavoriteLocalDataSource(cinemaDao: CinemaDao): FavoriteLocalDataSource =
        FavoriteLocalDataSourceImpl(cinemaDao)

    // SEARCH MODULE

    @Singleton
    @Provides
    fun provideSearchRemoteDataSource(retrofitService: RetrofitService): SearchRemoteDataSource =
        SearchRemoteDataSourceImpl(retrofitService)
}