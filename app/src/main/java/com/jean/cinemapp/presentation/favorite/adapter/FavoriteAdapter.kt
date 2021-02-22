package com.jean.cinemapp.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jean.cinemapp.R
import com.jean.cinemapp.databinding.ItemFavoriteBinding
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.BaseViewHolder
import com.jean.cinemapp.utils.loadMovieImage

class FavoriteAdapter(private val interaction: Interaction? = null): ListAdapter<Movie, BaseViewHolder<*>>(DiffCallback()) {

    private var mFavoriteList: List<Movie> = listOf()

    fun updateList(list: List<Movie>) {
        mFavoriteList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> =
        FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false))

    override fun getItemCount(): Int = mFavoriteList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as FavoriteViewHolder).bind(mFavoriteList[position], position)
    }

    inner class FavoriteViewHolder(itemView: View): BaseViewHolder<Movie>(itemView) {

        private val mBinding = ItemFavoriteBinding.bind(itemView)

        override fun bind(item: Movie, position: Int) = with(mBinding) {
            tvName.text = item.title
            tvRating.text = item.voteAverage.toString()
            tvDescription.text = item.overview
            if (!item.posterPath.isNullOrEmpty()) ivPoster.loadMovieImage(item.posterPath)
            root.setOnClickListener {
                interaction?.onItemSelected(position, item)
            }
            root.setOnLongClickListener {
                interaction?.onLongItemSelected(position, item)
                true
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Movie)
        fun onLongItemSelected(position: Int, item: Movie)
    }

    class DiffCallback: DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
    }
}