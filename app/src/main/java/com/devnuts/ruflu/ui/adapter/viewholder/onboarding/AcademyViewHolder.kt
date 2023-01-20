package com.devnuts.ruflu.ui.adapter.viewholder.onboarding

import com.devnuts.ruflu.databinding.ItemOnboardingAcademyBinding
import com.devnuts.ruflu.ui.adapter.viewholder.ModelViewHolder
import com.devnuts.ruflu.ui.model.onboarding.AcademyUIModel
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class AcademyViewHolder(
    private val binding: ItemOnboardingAcademyBinding
) : ModelViewHolder<AcademyUIModel>(binding){

    override fun bindData(model: AcademyUIModel) {
        binding.tvText.text = model.academy
        itemView.isSelected = model.isSelected
    }

    override fun bindViews(model: AcademyUIModel, adapterListener: ModelAdapterListener?) {
        itemView.setOnClickListener {
            adapterListener?.onClick(it, model, absoluteAdapterPosition)
        }
    }
}