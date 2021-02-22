package com.jean.cinemapp.di

import com.jean.cinemapp.domain.usecase.auth.ChangePassword
import com.jean.cinemapp.domain.usecase.auth.GetUserData
import com.jean.cinemapp.domain.usecase.auth.SignOut
import com.jean.cinemapp.domain.usecase.auth.SignUp
import com.jean.cinemapp.domain.usecase.favorite.DeleteAllFavorites
import com.jean.cinemapp.domain.usecase.favorite.DeleteFavorite
import com.jean.cinemapp.domain.usecase.favorite.GetFavoriteMovies
import com.jean.cinemapp.domain.usecase.favorite.SaveFavorite
import com.jean.cinemapp.domain.usecase.movie.*
import com.jean.cinemapp.domain.usecase.search.GetSearchMovies
import com.jean.cinemapp.presentation.auth.viewmodel.SignUpViewModel
import com.jean.cinemapp.presentation.favorite.viewmodel.FavoriteViewModel
import com.jean.cinemapp.presentation.movie.viewmodel.MovieDetailViewModel
import com.jean.cinemapp.presentation.movie.viewmodel.MovieViewModel
import com.jean.cinemapp.presentation.profile.viewmodel.ChangePasswordViewModel
import com.jean.cinemapp.presentation.profile.viewmodel.ProfileViewModel
import com.jean.cinemapp.presentation.search.viewmodel.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object ViewModelModule {

    // AUTHENTICATION MODULE

    @Provides
    fun provideSignUpViewModel(signUp: SignUp): SignUpViewModel = SignUpViewModel(signUp)

    // MOVIE MODULE

    @Provides
    fun provideMovieViewModel(getRecommendedMovies: GetRecommendedMovies, getInCinemaMovies: GetInCinemaMovies,
                              getNextReleaseMovies: GetNextReleaseMovies, getUserData: GetUserData): MovieViewModel =
        MovieViewModel(getRecommendedMovies, getInCinemaMovies, getNextReleaseMovies, getUserData)

    @Provides
    fun provideMovieDetailViewModel(getMovieCast: GetMovieCast, getFavoriteMovies: GetFavoriteMovies, saveFavorite: SaveFavorite, deleteFavorite: DeleteFavorite): MovieDetailViewModel =
        MovieDetailViewModel(getMovieCast, getFavoriteMovies, saveFavorite, deleteFavorite)

    // FAVORITE MODULE

    @Provides
    fun provideFavoriteViewModel(getFavoriteMovies: GetFavoriteMovies, deleteFavorite: DeleteFavorite, deleteAllFavorites: DeleteAllFavorites): FavoriteViewModel =
        FavoriteViewModel(getFavoriteMovies, deleteFavorite, deleteAllFavorites)

    // PROFILE MODULE

    @Provides
    fun provideProfileViewModel(signOut: SignOut, getUserData: GetUserData): ProfileViewModel =
        ProfileViewModel(signOut, getUserData)

    @Provides
    fun provideChangePasswordViewModel(changePassword: ChangePassword): ChangePasswordViewModel =
        ChangePasswordViewModel(changePassword)

    // SEARCH MODULE

    @Provides
    fun provideSearchViewModel(getSearchMovies: GetSearchMovies): SearchViewModel =
        SearchViewModel(getSearchMovies)
}