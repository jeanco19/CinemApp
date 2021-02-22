package com.jean.cinemapp.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jean.cinemapp.R
import com.jean.cinemapp.databinding.ItemCastBinding
import com.jean.cinemapp.domain.model.movie.Cast
import com.jean.cinemapp.utils.BaseViewHolder
import com.jean.cinemapp.utils.loadCastImage

class CastAdapter: ListAdapter<Cast, BaseViewHolder<*>>(DiffCallback()) {

    private var mCastList: List<Cast> = listOf()

    fun updateList(list: List<Cast>) {
        mCastList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> =
        CastViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false))

    override fun getItemCount(): Int = mCastList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as CastViewHolder).bind(mCastList[position], position)
    }

    inner class CastViewHolder(itemView: View): BaseViewHolder<Cast>(itemView) {

        private val mBinding = ItemCastBinding.bind(itemView)

        override fun bind(item: Cast, position: Int) = with(mBinding) {
            if (!item.profilePath.isNullOrEmpty()) ivPoster.loadCastImage(item.profilePath)
            tvName.text = item.name
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Cast>() {

        override fun areItemsTheSame(oldItem: Cast, newItem: Cast) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Cast, newItem: Cast) = oldItem == newItem
    }
}