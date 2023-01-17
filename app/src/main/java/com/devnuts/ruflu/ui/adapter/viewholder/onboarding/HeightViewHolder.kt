package com.devnuts.ruflu.ui.adapter.viewholder.onboarding

import com.devnuts.ruflu.databinding.ItemOnboardingHeightBinding
import com.devnuts.ruflu.ui.adapter.viewholder.ModelViewHolder
import com.devnuts.ruflu.ui.model.onboarding.HeightUIModel
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class HeightViewHolder(
    private val binding: ItemOnboardingHeightBinding
) : ModelViewHolder<HeightUIModel>(binding) {

    override fun bindData(model: HeightUIModel) {
        binding.tvText.text = model.height.toString()
        itemView.isSelected = model.isSelected
    }

    override fun bindViews(model: HeightUIModel, adapterListener: ModelAdapterListener?) {
        itemView.setOnClickListener {
            adapterListener?.onClick(it, model, absoluteAdapterPosition)
        }
    }
}