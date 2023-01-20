package com.devnuts.ruflu.ui.adapter.viewholder.onboarding

import com.devnuts.ruflu.databinding.ItemOnboardingFancyBinding
import com.devnuts.ruflu.ui.adapter.viewholder.ModelViewHolder
import com.devnuts.ruflu.ui.model.onboarding.FancyUIModel
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class FancyViewHolder(
    private val binding: ItemOnboardingFancyBinding
) : ModelViewHolder<FancyUIModel>(binding){

    override fun bindData(model: FancyUIModel) {
        binding.tvText.text = model.fancy
        itemView.isSelected = model.isSelected
    }

    override fun bindViews(model: FancyUIModel, adapterListener: ModelAdapterListener?) {
        itemView.setOnClickListener {
            adapterListener?.onClick(it, model, absoluteAdapterPosition)
        }
    }
}