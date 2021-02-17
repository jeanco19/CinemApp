package com.jean.cinemapp.presentation.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jean.cinemapp.R
import com.jean.cinemapp.databinding.ItemFavoriteBinding
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.loadMovieImage

class FavoriteAdapter(private val interaction: Interaction? = null): ListAdapter<Movie, FavoriteAdapter.ViewHolder>(DiffCallback()) {

    private var mFavoriteList: List<Movie> = listOf()

    fun updateList(list: List<Movie>) {
        mFavoriteList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false))

    override fun getItemCount(): Int = mFavoriteList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mFavoriteList[position], interaction, position)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val mBinding = ItemFavoriteBinding.bind(itemView)

        fun bind(movie: Movie, interaction: Interaction?, position: Int) = with(mBinding) {
            tvName.text = movie.title
            tvRating.text = movie.voteAverage.toString()
            tvDescription.text = movie.overview
            if (!movie.posterPath.isNullOrEmpty()) ivPoster.loadMovieImage(movie.posterPath)
            root.setOnClickListener {
                interaction?.onItemSelected(position, movie)
            }
            root.setOnLongClickListener {
                interaction?.onLongItemSelected(position, movie)
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