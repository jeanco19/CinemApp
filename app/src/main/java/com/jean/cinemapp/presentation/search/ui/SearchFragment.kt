package com.jean.cinemapp.presentation.search.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jean.cinemapp.R

import com.jean.cinemapp.databinding.FragmentSearchBinding
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.presentation.search.adapter.SearchAdapter
import com.jean.cinemapp.presentation.search.viewmodel.SearchViewModel
import com.jean.cinemapp.utils.Constants.NAVIGATION_FROM_SEARCH
import com.jean.cinemapp.utils.ErrorTypes
import com.jean.cinemapp.utils.Resource
import com.jean.cinemapp.utils.setupVerticalRecycler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment(), SearchAdapter.Interaction, SearchView.OnQueryTextListener {

    private var _mBinding: FragmentSearchBinding? = null
    private val mBinding get() = _mBinding!!
    private val mSearchViewModel by viewModels<SearchViewModel>()
    private lateinit var mSearchAdapter: SearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _mBinding = FragmentSearchBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.searchView.setOnQueryTextListener(this@SearchFragment)
        setupRecyclerAndAdapter()
    }

    private fun setupRecyclerAndAdapter() {
        mSearchAdapter = SearchAdapter(this@SearchFragment)
        mBinding.rvSearch.setupVerticalRecycler(requireContext(), mSearchAdapter, true)
    }

    override fun onItemSelected(position: Int, item: Movie) {
        findNavController().navigate(SearchFragmentDirections.actionNavigationSearchToMovieDetail(item, NAVIGATION_FROM_SEARCH))
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        searchMovie(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = false

    private fun searchMovie(query: String?) {
        if (query.isNullOrEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.error_must_write_the_word_to_search), Toast.LENGTH_LONG).show()
        } else {
            mSearchViewModel.getSearchMovies(query)
            mSearchViewModel.mSearchMovies.observe(viewLifecycleOwner, { result ->
                when (result) {
                    is Resource.Loading -> {
                        manageElementsVisibility(pbVisibility = true, rvVisibility = false, llVisibility = false)
                    }
                    is Resource.Success -> {
                        manageElementsVisibility(pbVisibility = false, rvVisibility = true, llVisibility = false)
                        result.data?.let { mSearchAdapter.updateList(it) }
                    }
                    is Resource.Error -> {
                        manageElementsVisibility(pbVisibility = false, rvVisibility = false, llVisibility = true)
                        manageError(result.errorType)
                    }
                }
            })
        }
    }

    private fun manageElementsVisibility(pbVisibility: Boolean, rvVisibility: Boolean, llVisibility: Boolean) {
        mBinding.pbSearch.visibility = if (pbVisibility) View.VISIBLE else View.GONE
        mBinding.rvSearch.visibility = if (rvVisibility) View.VISIBLE else View.INVISIBLE
        mBinding.llEmptyError.visibility = if (llVisibility) View.VISIBLE else View.INVISIBLE
    }

    private fun manageError(errorTypes: ErrorTypes?) {
        when (errorTypes) {
            ErrorTypes.EMPTY_LIST -> {
                mBinding.ivEmptyError.setImageResource(R.drawable.ic_search_off_black_24dp)
                mBinding.tvEmptyError.text = getString(R.string.error_empty_movies_list)
            }
            ErrorTypes.FAILED_RESPONSE -> {
                mBinding.ivEmptyError.setImageResource(R.drawable.ic_search_off_black_24dp)
                mBinding.tvEmptyError.text = getString(R.string.error_getting_search_movies_list)
            }
            ErrorTypes.FAILED_CONNECTION -> {
                mBinding.ivEmptyError.setImageResource(R.drawable.ic_connection_error_black_24dp)
                mBinding.tvEmptyError.text = getString(R.string.error_in_network)
            }
            ErrorTypes.WITHOUT_CONNECTION -> {
                mBinding.ivEmptyError.setImageResource(R.drawable.ic_signal_off_black_24dp)
                mBinding.tvEmptyError.text = getString(R.string.error_internet_connection)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}