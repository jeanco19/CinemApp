package com.jean.cinemapp.presentation.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jean.cinemapp.R
import com.jean.cinemapp.databinding.ItemOptionBinding
import com.jean.cinemapp.domain.model.profile.Option

class OptionAdapter(private val optionList: List<Option>,
                    private val interaction: Interaction? = null): RecyclerView.Adapter<OptionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_option, parent, false))

    override fun getItemCount(): Int = optionList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(optionList[position], interaction)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val mBinding = ItemOptionBinding.bind(itemView)

        fun bind(option: Option, interaction: Interaction?) = with(mBinding) {
            ivIcon.setImageResource(option.icon)
            tvTitle.text = option.title
            root.setOnClickListener {
                interaction?.onItemSelected(option.flag)
            }
        }
    }

    interface Interaction {
        fun onItemSelected(option: String)
    }
}