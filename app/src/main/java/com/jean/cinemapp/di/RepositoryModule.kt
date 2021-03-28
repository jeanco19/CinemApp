package com.jean.cinemapp.di

import com.jean.cinemapp.data.datasource.local.favorite.FavoriteLocalDataSource
import com.jean.cinemapp.data.datasource.local.movie.MovieLocalDataSource
import com.jean.cinemapp.data.datasource.remote.auth.AuthenticationDataSource
import com.jean.cinemapp.data.datasource.remote.movie.MovieRemoteDataSource
import com.jean.cinemapp.data.datasource.remote.search.SearchRemoteDataSource
import com.jean.cinemapp.data.repository.auth.AuthenticationRepositoryImpl
import com.jean.cinemapp.data.repository.favorite.FavoriteRepositoryImpl
import com.jean.cinemapp.data.repository.movie.MovieRepositoryImpl
import com.jean.cinemapp.data.repository.search.SearchRepositoryImpl
import com.jean.cinemapp.domain.repository.auth.AuthenticationRepository
import com.jean.cinemapp.domain.repository.favorite.FavoriteRepository
import com.jean.cinemapp.domain.repository.movie.MovieRepository
import com.jean.cinemapp.domain.repository.search.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    // AUTHENTICATION MODULE

    @Singleton
    @Provides
    fun provideAuthenticationRepository(authenticationDataSource: AuthenticationDataSource): AuthenticationRepository =
        AuthenticationRepositoryImpl(authenticationDataSource)

    // MOVIE MODULE

    @Singleton
    @Provides
    fun provideMovieRepository(movieRemoteDataSource: MovieRemoteDataSource, movieLocalDataSource: MovieLocalDataSource): MovieRepository =
        MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)

    // FAVORITE MODULE

    @Singleton
    @Provides
    fun provideFavoriteRepository(favoriteLocalDataSource: FavoriteLocalDataSource): FavoriteRepository =
        FavoriteRepositoryImpl(favoriteLocalDataSource)

    // SEARCH MODULE

    @Singleton
    @Provides
    fun provideSearchRepository(searchRemoteDataSource: SearchRemoteDataSource): SearchRepository =
        SearchRepositoryImpl(searchRemoteDataSource)
}