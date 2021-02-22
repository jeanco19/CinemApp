package com.jean.cinemapp.presentation.profile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jean.cinemapp.R
import com.jean.cinemapp.databinding.ItemOptionBinding
import com.jean.cinemapp.domain.model.profile.Option
import com.jean.cinemapp.utils.BaseViewHolder

class OptionAdapter(private val optionList: List<Option>,
                    private val interaction: Interaction? = null): RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> =
        OptionViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_option, parent, false))

    override fun getItemCount(): Int = optionList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        (holder as OptionViewHolder).bind(optionList[position], position)
    }

    inner class OptionViewHolder(itemView: View): BaseViewHolder<Option>(itemView) {

        private val mBinding = ItemOptionBinding.bind(itemView)

        override fun bind(item: Option, position: Int) = with(mBinding) {
            ivIcon.setImageResource(item.icon)
            tvTitle.text = item.title
            root.setOnClickListener {
                interaction?.onItemSelected(item.flag)
            }
        }
    }

    interface Interaction {
        fun onItemSelected(option: String)
    }
}