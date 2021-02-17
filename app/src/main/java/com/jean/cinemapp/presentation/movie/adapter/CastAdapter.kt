package com.jean.cinemapp.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jean.cinemapp.R
import com.jean.cinemapp.databinding.ItemCastBinding
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.utils.loadCastImage
import com.jean.cinemapp.utils.loadMovieImage

class CastAdapter: ListAdapter<Cast, CastAdapter.ViewHolder>(DiffCallback()) {

    private var mCastList: List<Cast> = listOf()

    fun updateList(list: List<Cast>) {
        mCastList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false))

    override fun getItemCount(): Int = mCastList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(mCastList[position])

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val mBinding = ItemCastBinding.bind(view)

        fun bind(cast: Cast) = with(mBinding) {
            if (!cast.profilePath.isNullOrEmpty()) ivPoster.loadCastImage(cast.profilePath)
            tvName.text = cast.name
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Cast>() {

        override fun areItemsTheSame(oldItem: Cast, newItem: Cast) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast) = oldItem == newItem
    }
}