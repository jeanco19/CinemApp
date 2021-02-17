package com.jean.cinemapp.presentation.favorite.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.domain.usecase.favorite.DeleteAllFavorites
import com.jean.cinemapp.domain.usecase.favorite.DeleteFavorite
import com.jean.cinemapp.domain.usecase.favorite.GetFavoriteMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel @ViewModelInject constructor(private val getFavoriteMovies: GetFavoriteMovies,
                                                     private val deleteFavorite: DeleteFavorite,
                                                     private val deleteAllFavorites: DeleteAllFavorites): ViewModel() {


    val mFavoriteMovies = getFavoriteMovies.invoke().asLiveData()

    fun deleteFavoriteMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        deleteFavorite.invoke(movie)
    }

    fun deleteAllFavorites() = viewModelScope.launch(Dispatchers.IO) {
        deleteAllFavorites.invoke()
    }
}