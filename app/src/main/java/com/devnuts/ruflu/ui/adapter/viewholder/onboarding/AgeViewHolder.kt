package com.devnuts.ruflu.ui.adapter.viewholder.onboarding

import com.devnuts.ruflu.databinding.ItemOnboardingAgeBinding
import com.devnuts.ruflu.ui.adapter.viewholder.ModelViewHolder
import com.devnuts.ruflu.ui.model.onboarding.AgeUIModel
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class AgeViewHolder(
    private val binding: ItemOnboardingAgeBinding
) : ModelViewHolder<AgeUIModel>(binding) {

    override fun bindData(model: AgeUIModel) {
        binding.tvText.text = model.age.toString()
        itemView.isSelected = model.isSelected

    }

    override fun bindViews(model: AgeUIModel, adapterListener: ModelAdapterListener?) {
        itemView.setOnClickListener {
            adapterListener?.onClick(it, model, absoluteAdapterPosition)
        }


    }
}