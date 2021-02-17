package com.jean.cinemapp.presentation.movie.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jean.cinemapp.R

import com.jean.cinemapp.databinding.FragmentHomeBinding
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.presentation.movie.adapter.MovieAdapter
import com.jean.cinemapp.presentation.movie.viewmodel.MovieViewModel
import com.jean.cinemapp.utils.*
import com.jean.cinemapp.utils.Constants.MOVIE_IN_CINEMA
import com.jean.cinemapp.utils.Constants.MOVIE_NEXT_RELEASE
import com.jean.cinemapp.utils.Constants.MOVIE_RECOMMENDED
import com.jean.cinemapp.utils.Constants.NAVIGATION_FROM_HOME
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment: Fragment(), MovieAdapter.Interaction {

    private var _mBinding: FragmentHomeBinding? = null
    private val mBinding get() = _mBinding!!
    private val mMovieViewModel by viewModels<MovieViewModel>()
    private lateinit var mRecommendedAdapter: MovieAdapter
    private lateinit var mInCinemaAdapter: MovieAdapter
    private lateinit var mNextReleaseAdapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _mBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserData()
        setupAdapters()
        setupRecyclerViews()
        getRecommendedMovies()
        getInCinemaMovies()
        getNextReleaseMovies()
    }

    private fun setUserData() {
        val user = mMovieViewModel.getUserData()
        user?.let { data ->
            mBinding.tvUserName.text = String.format(Locale.ROOT, getString(R.string.title_hello_user), data.name)
        }
    }

    private fun setupAdapters() {
        mRecommendedAdapter = MovieAdapter(this@HomeFragment)
        mInCinemaAdapter = MovieAdapter(this@HomeFragment)
        mNextReleaseAdapter = MovieAdapter(this@HomeFragment)
    }

    private fun setupRecyclerViews() {
        mBinding.rvRecommended.setupHorizontalRecycler(requireContext(), mRecommendedAdapter)
        mBinding.rvInCinema.setupHorizontalRecycler(requireContext(), mInCinemaAdapter)
        mBinding.rvNextReleases.setupHorizontalRecycler(requireContext(), mNextReleaseAdapter)
    }

    private fun getRecommendedMovies() {
        mMovieViewModel.getRecommendedMovies()
        mMovieViewModel.mRecommendedMovies.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    manageElementsVisibility(pbVisibility = true, rvVisibility = false, tvVisibility = false, MOVIE_RECOMMENDED)
                }
                is Resource.Success -> {
                    manageElementsVisibility(pbVisibility = false, rvVisibility = true, tvVisibility = false, MOVIE_RECOMMENDED)
                    result.data?.let { mRecommendedAdapter.updateList(it) }
                }
                is Resource.Error -> {
                    manageElementsVisibility(pbVisibility = false, rvVisibility = false, tvVisibility = true, MOVIE_RECOMMENDED)
                    manageError(result, MOVIE_RECOMMENDED, mBinding.tvErrorRecommended)
                }
            }
        })
    }

    private fun getInCinemaMovies() {
        mMovieViewModel.getInCinemaMovies()
        mMovieViewModel.mInCinemaMovies.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    manageElementsVisibility(pbVisibility = true, rvVisibility = false, tvVisibility = false, MOVIE_IN_CINEMA)
                }
                is Resource.Success -> {
                    manageElementsVisibility(pbVisibility = false, rvVisibility = true, tvVisibility = false, MOVIE_IN_CINEMA)
                    result.data?.let { mInCinemaAdapter.updateList(it) }
                }
                is Resource.Error -> {
                    manageElementsVisibility(pbVisibility = false, rvVisibility = false, tvVisibility = true, MOVIE_IN_CINEMA)
                    manageError(result, MOVIE_IN_CINEMA, mBinding.tvErrorInCinema)
                }
            }
        })
    }

    private fun getNextReleaseMovies() {
        mMovieViewModel.getNextReleaseMovies()
        mMovieViewModel.mNextReleaseMovies.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    manageElementsVisibility(pbVisibility = true, rvVisibility = false, tvVisibility = false, MOVIE_NEXT_RELEASE)
                }
                is Resource.Success -> {
                    manageElementsVisibility(pbVisibility = false, rvVisibility = true, tvVisibility = false, MOVIE_NEXT_RELEASE)
                    result.data?.let { mNextReleaseAdapter.updateList(it) }
                }
                is Resource.Error -> {
                    manageElementsVisibility(pbVisibility = false, rvVisibility = false, tvVisibility = true, MOVIE_NEXT_RELEASE)
                    manageError(result, MOVIE_NEXT_RELEASE, mBinding.tvErrorNextReleases)
                }
            }
        })
    }

    private fun manageElementsVisibility(pbVisibility: Boolean, rvVisibility: Boolean, tvVisibility: Boolean, movieType: String) {
        when (movieType) {
            MOVIE_RECOMMENDED -> {
                mBinding.pbRecommended.visibility = if (pbVisibility) View.VISIBLE else View.GONE
                mBinding.rvInCinema.visibility = if (rvVisibility) View.VISIBLE else View.INVISIBLE
                mBinding.tvErrorRecommended.visibility = if (tvVisibility) View.VISIBLE else View.INVISIBLE
            }
            MOVIE_IN_CINEMA -> {
                mBinding.pbInCinema.visibility = if (pbVisibility) View.VISIBLE else View.GONE
                mBinding.rvInCinema.visibility = if (rvVisibility) View.VISIBLE else View.INVISIBLE
                mBinding.tvErrorInCinema.visibility = if (tvVisibility) View.VISIBLE else View.INVISIBLE
            }
            MOVIE_NEXT_RELEASE -> {
                mBinding.pbNextReleases.visibility = if (pbVisibility) View.VISIBLE else View.GONE
                mBinding.rvNextReleases.visibility = if (rvVisibility) View.VISIBLE else View.INVISIBLE
                mBinding.tvErrorNextReleases.visibility = if (tvVisibility) View.VISIBLE else View.INVISIBLE
            }
        }
    }

    private fun manageError(result: Resource.Error<List<Movie>>, movieType: String, tvError: TextView) {
        when (result.errorType) {
            ErrorTypes.EMPTY_LIST -> {
                tvError.text = getString(R.string.error_empty_cast_list)
            }
            ErrorTypes.FAILED_RESPONSE -> {
                tvError.text = getString(R.string.error_empty_cast_list)
                Toast.makeText(requireContext(), getString(R.string.error_getting_cast_list), Toast.LENGTH_LONG).show()
            }
            ErrorTypes.FAILED_CONNECTION -> {
                tvError.text = getString(R.string.error_empty_cast_list)
                Toast.makeText(requireContext(), getString(R.string.error_in_network), Toast.LENGTH_LONG).show()
            }
            ErrorTypes.WITHOUT_CONNECTION -> {
                result.data?.let { movies ->
                    manageElementsVisibility(pbVisibility = false, rvVisibility = true, tvVisibility = false, movieType)
                    when (movieType) {
                        MOVIE_RECOMMENDED -> { mRecommendedAdapter.updateList(movies) }
                        MOVIE_IN_CINEMA -> { mInCinemaAdapter.updateList(movies) }
                        MOVIE_NEXT_RELEASE -> { mNextReleaseAdapter.updateList(movies) }
                    }
                }
                tvError.text = getString(R.string.error_empty_cast_list)
                Snackbar.make(mBinding.root, getString(R.string.error_internet_connection), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onItemSelected(position: Int, item: Movie) {
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToMovieDetail(item, NAVIGATION_FROM_HOME))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}