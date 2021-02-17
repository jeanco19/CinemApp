package com.jean.cinemapp.presentation.movie.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.domain.usecase.auth.GetUserData
import com.jean.cinemapp.domain.usecase.movie.GetInCinemaMovies
import com.jean.cinemapp.domain.usecase.movie.GetNextReleaseMovies
import com.jean.cinemapp.domain.usecase.movie.GetRecommendedMovies
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel @ViewModelInject constructor(private val getRecommendedMovies: GetRecommendedMovies,
                                                  private val getInCinemaMovies: GetInCinemaMovies,
                                                  private val getNextReleaseMovies: GetNextReleaseMovies,
                                                  private val getUserData: GetUserData): ViewModel() {

    private val _mRecommendedMovies = MutableLiveData<Resource<List<Movie>>>()
    val mRecommendedMovies: LiveData<Resource<List<Movie>>> get() = _mRecommendedMovies
    private val _mInCinemaMovies = MutableLiveData<Resource<List<Movie>>>()
    val mInCinemaMovies: LiveData<Resource<List<Movie>>> get() =  _mInCinemaMovies
    private val _mNextReleaseMovies = MutableLiveData<Resource<List<Movie>>>()
    val mNextReleaseMovies: LiveData<Resource<List<Movie>>> get() = _mNextReleaseMovies

    fun getRecommendedMovies() = viewModelScope.launch(Dispatchers.IO) {
        _mRecommendedMovies.postValue(Resource.Loading())
        _mRecommendedMovies.postValue(getRecommendedMovies.invoke())
    }

    fun getInCinemaMovies() = viewModelScope.launch(Dispatchers.IO) {
        _mInCinemaMovies.postValue(Resource.Loading())
        _mInCinemaMovies.postValue(getInCinemaMovies.invoke())
    }

    fun getNextReleaseMovies() = viewModelScope.launch(Dispatchers.IO) {
        _mNextReleaseMovies.postValue(Resource.Loading())
        _mNextReleaseMovies.postValue(getNextReleaseMovies.invoke())
    }

    fun getUserData() = getUserData.invoke()
}