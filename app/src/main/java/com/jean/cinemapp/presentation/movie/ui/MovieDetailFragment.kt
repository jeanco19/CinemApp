package com.jean.cinemapp.presentation.movie.ui

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.jean.cinemapp.R
import com.jean.cinemapp.databinding.FragmentMovieDetailBinding
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.presentation.movie.adapter.CastAdapter
import com.jean.cinemapp.presentation.movie.viewmodel.MovieDetailViewModel
import com.jean.cinemapp.utils.*
import com.jean.cinemapp.utils.Constants.NAVIGATION_FROM_FAVORITE
import com.jean.cinemapp.utils.Constants.NAVIGATION_FROM_SEARCH
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment: Fragment() {

    private var _mBinding: FragmentMovieDetailBinding? = null
    private val mBinding get() = _mBinding!!
    private val mMovieDetailViewModel by viewModels<MovieDetailViewModel>()
    private val mCastAdapter: CastAdapter by lazy { CastAdapter() }
    private val mArgs by navArgs<MovieDetailFragmentArgs>()
    private var mMovie: Movie? = null
    private var mNavFrom = ""
    private var mIsFavorite = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _mBinding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgumentsData()
        setUpListeners()
        mBinding.rvCast.setupHorizontalRecycler(requireContext(), mCastAdapter)
        setMovieData()
    }

    private fun getArgumentsData() {
        mArgs.navigationFrom?.let { mNavFrom = it }
        mArgs.currentMovie?.let { mMovie = it }
    }

    private fun setUpListeners() {
        mBinding.ivArrowBack.setOnClickListener {
            when (mNavFrom) {
                NAVIGATION_FROM_FAVORITE -> {
                    findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailToNavigationFavorite())
                }
                NAVIGATION_FROM_SEARCH -> {
                    findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailToNavigationSearch())
                }
                else -> {
                    findNavController().navigate(MovieDetailFragmentDirections.actionMovieDetailToNavigationHome())
                }
            }
        }
        mBinding.fabFavorite.setOnClickListener {
            if (mIsFavorite) deleteFavoriteMovie()
            else saveFavoriteMovie()
        }
    }

    private fun setMovieData() {
        mMovie?.let { movie ->
            fillMovieInfo(movie)
            getMovieCast(movie.id.toString())
            checkIsFavorite(movie)
        }
    }

    private fun fillMovieInfo(movie: Movie) {
        if (movie.posterPath.isNotEmpty()) mBinding.ivPoster.loadMovieImage(movie.posterPath)
        mBinding.tvName.text = movie.title
        mBinding.tvRating.text = movie.voteAverage.toString()
        mBinding.tvDescription.text = movie.overview
        mBinding.tvReleaseDate.text = movie.releaseDate
        mBinding.tvPopularity.text = movie.popularity.toString()
    }

    private fun getMovieCast(movieId: String) {
        mMovieDetailViewModel.getMovieCast(movieId)
        mMovieDetailViewModel.mMovieCast.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    manageElementsVisibility(pbVisibility = true, rvVisibility = false, tvVisibility = false)
                }
                is Resource.Success -> {
                    manageElementsVisibility(pbVisibility = false, rvVisibility = true, tvVisibility = false)
                    result.data?.let { mCastAdapter.updateList(it) }
                }
                is Resource.Error -> {
                    manageElementsVisibility(pbVisibility = false, rvVisibility = false, tvVisibility = true)
                    manageError(result)

                }
            }
        })
    }

    private fun manageError(result: Resource.Error<List<Cast>>) {
        when (result.errorType) {
            ErrorTypes.EMPTY_LIST -> {
                mBinding.tvEmptyError.text = getString(R.string.error_empty_cast_list)
            }
            ErrorTypes.FAILED_RESPONSE -> {
                mBinding.tvEmptyError.text = getString(R.string.error_empty_cast_list)
                Toast.makeText(requireContext(), getString(R.string.error_getting_cast_list), Toast.LENGTH_LONG).show()
            }
            ErrorTypes.FAILED_CONNECTION -> {
                mBinding.tvEmptyError.text = getString(R.string.error_empty_cast_list)
                Toast.makeText(requireContext(), getString(R.string.error_in_network), Toast.LENGTH_LONG).show()
            }
            ErrorTypes.WITHOUT_CONNECTION -> {
                result.data?.let { cast ->
                    manageElementsVisibility(pbVisibility = false, rvVisibility = true, tvVisibility = false)
                    mCastAdapter.updateList(cast)
                }
                mBinding.tvEmptyError.text = getString(R.string.error_empty_cast_list)
                Snackbar.make(mBinding.root, getString(R.string.error_internet_connection), Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun manageElementsVisibility(pbVisibility: Boolean, rvVisibility: Boolean, tvVisibility: Boolean) {
        mBinding.pbCast.visibility = if (pbVisibility) View.VISIBLE else View.GONE
        mBinding.rvCast.visibility = if (rvVisibility) View.VISIBLE else View.INVISIBLE
        mBinding.tvEmptyError.visibility = if (tvVisibility) View.VISIBLE else View.INVISIBLE
    }

    private fun checkIsFavorite(currentMovie: Movie) {
        mMovieDetailViewModel.mFavoriteMovies.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let { favorites ->
                        for (favoriteMovie in favorites) {
                            if (favoriteMovie.id == currentMovie.id) {
                                mIsFavorite = true
                                changeFavoriteButtonColor(R.color.color_primary)
                            }
                        }
                    }
                }
            }
        })
    }

    private fun saveFavoriteMovie() {
        mMovie?.let { movie ->
            mMovieDetailViewModel.saveFavoriteMovie(movie)
            changeFavoriteButtonColor(R.color.color_primary)
            Toast.makeText(requireContext(), getString(R.string.favorite_movie_added), Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteFavoriteMovie() {
        mMovie?.let { movie ->
            mIsFavorite = false
            mMovieDetailViewModel.deleteFavoriteMovie(movie)
            changeFavoriteButtonColor(android.R.color.darker_gray)
            Toast.makeText(requireContext(), getString(R.string.favorite_movie_deleted), Toast.LENGTH_SHORT).show()
        }
    }

    private fun changeFavoriteButtonColor(color: Int) {
        mBinding.fabFavorite.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), color))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}