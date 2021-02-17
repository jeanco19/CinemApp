package com.jean.cinemapp.di

import com.jean.cinemapp.domain.repository.auth.AuthenticationRepository
import com.jean.cinemapp.domain.repository.favorite.FavoriteRepository
import com.jean.cinemapp.domain.repository.movie.MovieDetailRepository
import com.jean.cinemapp.domain.repository.movie.MovieRepository
import com.jean.cinemapp.domain.repository.search.SearchRepository
import com.jean.cinemapp.domain.usecase.auth.*
import com.jean.cinemapp.domain.usecase.favorite.*
import com.jean.cinemapp.domain.usecase.movie.*
import com.jean.cinemapp.domain.usecase.search.GetSearchMovies
import com.jean.cinemapp.domain.usecase.search.GetSearchMoviesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCaseModule {

    // AUTHENTICATION MODULE

    @Provides
    fun provideSignUp(authenticationRepository: AuthenticationRepository): SignUp =
        SignUpImpl(authenticationRepository)

    @Provides
    fun provideSignIn(authenticationRepository: AuthenticationRepository): SignIn =
        SignInImpl(authenticationRepository)

    @Provides
    fun provideSignOut(authenticationRepository: AuthenticationRepository): SignOut =
        SignOutImpl(authenticationRepository)

    @Provides
    fun provideRecoverPassword(authenticationRepository: AuthenticationRepository): RecoverPassword =
        RecoverPasswordImpl(authenticationRepository)

    @Provides
    fun provideGetUserData(authenticationRepository: AuthenticationRepository): GetUserData =
        GetUserDataImpl(authenticationRepository)

    // MOVIE MODULE

    @Provides
    fun provideGetRecommendedMovies(movieRepository: MovieRepository): GetRecommendedMovies =
        GetRecommendedMoviesImpl(movieRepository)

    @Provides
    fun provideInCinemaMovies(movieRepository: MovieRepository): GetInCinemaMovies =
        GetInCinemaMoviesImpl(movieRepository)

    @Provides
    fun provideGetNextReleaseMovies(movieRepository: MovieRepository): GetNextReleaseMovies =
        GetNextReleaseMoviesImpl(movieRepository)

    @Provides
    fun provideGetMovieCast(movieDetailRepository: MovieDetailRepository): GetMovieCast =
        GetMovieCastImpl(movieDetailRepository)

    // FAVORITE MODULE

    @Provides
    fun provideGetFavoriteMovies(favoriteRepository: FavoriteRepository): GetFavoriteMovies =
        GetFavoriteMoviesImpl(favoriteRepository)

    @Provides
    fun provideSaveFavorite(favoriteRepository: FavoriteRepository): SaveFavorite =
        SaveFavoriteImpl(favoriteRepository)

    @Provides
    fun provideDeleteFavorite(favoriteRepository: FavoriteRepository): DeleteFavorite =
        DeleteFavoriteImpl(favoriteRepository)

    @Provides
    fun deleteAllFavorites(favoriteRepository: FavoriteRepository): DeleteAllFavorites =
        DeleteAllFavoritesImpl(favoriteRepository)

    // SEARCH MODULE

    @Provides
    fun provideGetSearchMovies(searchRepository: SearchRepository): GetSearchMovies =
        GetSearchMoviesImpl(searchRepository)
}