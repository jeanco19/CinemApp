package com.jean.cinemapp.presentation.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jean.cinemapp.R
import com.jean.cinemapp.databinding.ItemSearchBinding
import com.jean.cinemapp.domain.model.movie.Movie
import com.jean.cinemapp.utils.loadMovieImage

class SearchAdapter(private val interaction: Interaction? = null): ListAdapter<Movie, SearchAdapter.ViewHolder>(DiffCallback()) {

    private var mSearchList: List<Movie> = listOf()

    fun updateList(list: List<Movie>) {
        mSearchList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false))

    override fun getItemCount(): Int = mSearchList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mSearchList[position], interaction, position)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val mBinding = ItemSearchBinding.bind(itemView)

        fun bind(movie: Movie, interaction: Interaction?, position: Int) = with(mBinding) {
            tvName.text = movie.title
            tvRating.text = movie.voteAverage.toString()
            tvDescription.text = movie.overview
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

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean = oldItem == newItem
    }
}