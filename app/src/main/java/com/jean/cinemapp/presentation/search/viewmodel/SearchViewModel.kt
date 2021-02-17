package com.jean.cinemapp.presentation.search.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.domain.usecase.search.GetSearchMovies
import com.jean.cinemapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(private val getSearchMovies: GetSearchMovies): ViewModel() {

    private val _mSearchMovies = MutableLiveData<Resource<List<Movie>>>()
    val mSearchMovies: LiveData<Resource<List<Movie>>> get() = _mSearchMovies

    fun getSearchMovies(query: String) = viewModelScope.launch(Dispatchers.IO) {
        _mSearchMovies.postValue(Resource.Loading())
        _mSearchMovies.postValue(getSearchMovies.invoke(query))
    }
}