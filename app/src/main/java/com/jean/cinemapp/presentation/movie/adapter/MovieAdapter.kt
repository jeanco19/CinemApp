package com.jean.cinemapp.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jean.cinemapp.R
import com.jean.cinemapp.databinding.ItemMovieBinding
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.loadMovieImage

class MovieAdapter(private val interaction: Interaction? = null): ListAdapter<Movie, MovieAdapter.ViewHolder>(DiffCallback()) {

    private var mMovieList: List<Movie> = listOf()

    fun updateList(list: List<Movie>) {
        mMovieList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun getItemCount(): Int = mMovieList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mMovieList[position], interaction, position)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val mBinding = ItemMovieBinding.bind(view)

        fun bind(movie: Movie, interaction: Interaction?, position: Int) = with(mBinding) {
            tvName.text = movie.title
            tvRating.text = movie.voteAverage.toString()
            if (!movie.posterPath.isNullOrEmpty()) ivPoster.loadMovieImage(movie.posterPath)
            root.setOnClickListener {
                interaction?.onItemSelected(position, movie)
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