package com.jean.cinemapp.presentation.movie.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.domain.usecase.favorite.DeleteFavorite
import com.jean.cinemapp.domain.usecase.favorite.GetFavoriteMovies
import com.jean.cinemapp.domain.usecase.favorite.SaveFavorite
import com.jean.cinemapp.domain.usecase.movie.GetMovieCast
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel @ViewModelInject constructor(private val getMovieCast: GetMovieCast,
                                                        private val getFavoriteMovies: GetFavoriteMovies,
                                                        private val saveFavorite: SaveFavorite,
                                                        private val deleteFavorite: DeleteFavorite): ViewModel() {

    private var _mMovieCast = MutableLiveData<Resource<List<Cast>>>()
    val mMovieCast: LiveData<Resource<List<Cast>>> get() = _mMovieCast

    val mFavoriteMovies = getFavoriteMovies.invoke().asLiveData()

    fun getMovieCast(movieId: String) = viewModelScope.launch(Dispatchers.IO) {
        _mMovieCast.postValue(Resource.Loading())
        _mMovieCast.postValue(getMovieCast.invoke(movieId))
    }

    fun saveFavoriteMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        saveFavorite.invoke(movie)
    }

    fun deleteFavoriteMovie(movie: Movie) = viewModelScope.launch(Dispatchers.IO) {
        deleteFavorite.invoke(movie)
    }
}