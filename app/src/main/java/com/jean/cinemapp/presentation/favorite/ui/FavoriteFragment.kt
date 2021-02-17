package com.jean.cinemapp.presentation.favorite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jean.cinemapp.R

import com.jean.cinemapp.databinding.FragmentFavoriteBinding
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.presentation.favorite.adapter.FavoriteAdapter
import com.jean.cinemapp.presentation.favorite.viewmodel.FavoriteViewModel
import com.jean.cinemapp.utils.Constants.NAVIGATION_FROM_FAVORITE
import com.jean.cinemapp.utils.Resource
import com.jean.cinemapp.utils.setupVerticalRecycler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment: Fragment(), FavoriteAdapter.Interaction {

    private var _mBinding: FragmentFavoriteBinding? = null
    private val mBinding get() = _mBinding!!
    private val mFavoriteViewModel by viewModels<FavoriteViewModel>()
    private lateinit var mFavoriteAdapter: FavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _mBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupRecyclerAndAdapter()
        getFavoritesMovies()
    }

    private fun setupListeners() {
        mBinding.tvDeleteAll.setOnClickListener {
            showDeleteAllFavoritesDialog()
        }
    }

    private fun setupRecyclerAndAdapter() {
        mFavoriteAdapter = FavoriteAdapter(this@FavoriteFragment)
        mBinding.rvFavorite.setupVerticalRecycler(requireContext(), mFavoriteAdapter, false)
    }

    private fun getFavoritesMovies() {
        mFavoriteViewModel.mFavoriteMovies.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Success -> {
                    manageElementsVisibility(rvVisibility = true, llVisibility = false, tvVisibility = true)
                    result.data?.let { mFavoriteAdapter.updateList(it) }
                }
                is Resource.Error -> {
                    manageElementsVisibility(rvVisibility = false, llVisibility = true, tvVisibility = false)
                }
            }
        })
    }

    private fun manageElementsVisibility(rvVisibility: Boolean, llVisibility: Boolean, tvVisibility: Boolean) {
        mBinding.rvFavorite.visibility = if (rvVisibility) View.VISIBLE else View.INVISIBLE
        mBinding.llEmptyError.visibility = if (llVisibility) View.VISIBLE else View.INVISIBLE
        mBinding.tvDeleteAll.visibility = if (tvVisibility) View.VISIBLE else View.INVISIBLE
    }

    override fun onItemSelected(position: Int, item: Movie) {
        findNavController().navigate(FavoriteFragmentDirections.actionNavigationFavoriteToMovieDetail(item, NAVIGATION_FROM_FAVORITE))
    }

    override fun onLongItemSelected(position: Int, item: Movie) {
        showDeleteFavoriteDialog(item)
    }

    private fun showDeleteFavoriteDialog(item: Movie) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_delete_favorite_title))
            .setMessage(getString(R.string.dialog_delete_favorite_message))
            .setPositiveButton(getString(R.string.dialog_delete_button)) { dialog, which ->
                mFavoriteViewModel.deleteFavoriteMovie(item)
                Toast.makeText(requireContext(), getString(R.string.favorite_movie_deleted), Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(getString(R.string.dialog_cancel_button), null)
            .show()
    }

    private fun showDeleteAllFavoritesDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_delete_all_favorites_title))
            .setMessage(getString(R.string.dialog_delete_all_favorites_message))
            .setPositiveButton(getString(R.string.dialog_delete_button)) { dialog, which ->
                mFavoriteViewModel.deleteAllFavorites()
                Toast.makeText(requireContext(), getString(R.string.favorites_movies_deleted), Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton(getString(R.string.dialog_cancel_button), null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mBinding = null
    }
}