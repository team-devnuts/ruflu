package com.devnuts.ruflu.ui.adapter.viewholder.onboarding

import com.devnuts.ruflu.databinding.ItemOnboardingGenderBinding
import com.devnuts.ruflu.ui.adapter.viewholder.ModelViewHolder
import com.devnuts.ruflu.ui.model.onboarding.GenderUIModel
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class GenderViewHolder(
    private val binding: ItemOnboardingGenderBinding
) : ModelViewHolder<GenderUIModel>(binding) {

    override fun bindData(model: GenderUIModel) {
        binding.ivPic.setImageResource(model.resourceId)
        binding.tvText.text = model.name
    }

    override fun bindViews(model: GenderUIModel, adapterListener: ModelAdapterListener?) {
        itemView.setOnClickListener {
            adapterListener?.onClick(it, model, absoluteAdapterPosition)
        }
    }
}