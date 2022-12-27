package com.devnuts.ruflu.ui.adapter.viewholder

import com.devnuts.ruflu.databinding.ItemUserCardBinding
import com.devnuts.ruflu.ui.model.home.UserCardUIModel
import com.devnuts.ruflu.util.UserUtil
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class UserCardViewHolder(
    private val binding: ItemUserCardBinding
) : ModelViewHolder<UserCardUIModel>(binding) {
    override fun bindData(model: UserCardUIModel) {
        binding.cardViewName.text = model.nick_nm
        binding.cardViewAge.text = "${UserUtil.getAge(model.birth)}"
    }

    override fun bindViews(model: UserCardUIModel, menuAdapterListener: ModelAdapterListener?) {

    }

    override fun bindDataWithPayLoads(model: UserCardUIModel, payload: MutableList<Any>) {
        super.bindDataWithPayLoads(model, payload)
    }
}