package com.jean.cinemapp.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jean.cinemapp.R
import com.jean.cinemapp.databinding.ItemMovieBinding
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.BaseViewHolder
import com.jean.cinemapp.utils.loadMovieImage

class MovieAdapter(private val interaction: Interaction? = null): ListAdapter<Movie, BaseViewHolder<*>>(DiffCallback()) {

    private var mMovieList: List<Movie> = listOf()

    fun updateList(list: List<Movie>) {
        mMovieList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> =
        MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun getItemCount(): Int = mMovieList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as MovieViewHolder).bind(mMovieList[position], position)
    }

    inner class MovieViewHolder(itemView: View): BaseViewHolder<Movie>(itemView) {

        private val mBinding = ItemMovieBinding.bind(itemView)

        override fun bind(item: Movie, position: Int) = with(mBinding) {
            tvName.text = item.title
            tvRating.text = item.voteAverage.toString()
            if (!item.posterPath.isNullOrEmpty()) ivPoster.loadMovieImage(item.posterPath)
            root.setOnClickListener {
                interaction?.onItemSelected(position, item)
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Movie)
    }

    class DiffCallback: DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) = oldItem == newItem
    }
}