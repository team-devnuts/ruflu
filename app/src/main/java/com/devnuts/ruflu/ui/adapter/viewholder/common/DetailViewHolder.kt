package com.devnuts.ruflu.ui.adapter.viewholder.common

import com.devnuts.ruflu.databinding.ItemUserDetailBinding
import com.devnuts.ruflu.ui.adapter.viewholder.ModelViewHolder
import com.devnuts.ruflu.ui.model.home.DetailUIModel
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class DetailViewHolder(
    private val binding: ItemUserDetailBinding
) : ModelViewHolder<DetailUIModel>(binding) {

    override fun bindData(model: DetailUIModel) {
        binding.tvUserDetailTitle.text = model.title
        binding.tvUserDetailValue.text = model.value
    }

    override fun bindViews(model: DetailUIModel, adapterListener: ModelAdapterListener?) {
        super.bindViews(model, adapterListener)
    }
}