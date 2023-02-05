package com.devnuts.ruflu.ui.adapter.viewholder.like

import com.devnuts.ruflu.databinding.ItemMatchUserBinding
import com.devnuts.ruflu.ui.adapter.viewholder.ModelViewHolder
import com.devnuts.ruflu.ui.model.home.UserUIModel
import com.devnuts.ruflu.util.UserUtil
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class MatchViewHolder(
    private val binding: ItemMatchUserBinding
) : ModelViewHolder<UserUIModel>(binding) {

    override fun bindData(model: UserUIModel) {
        binding.clvImage.tag = model.images[0]
        binding.tvNickName.text = model.nickName
        binding.tvAge.text = UserUtil.getAge(model.age).toString()
    }

    override fun bindViews(model: UserUIModel, adapterListener: ModelAdapterListener?) {
        binding.clvImage.setOnClickListener {
            adapterListener?.onClick(itemView,  model, absoluteAdapterPosition)
        }
    }
}