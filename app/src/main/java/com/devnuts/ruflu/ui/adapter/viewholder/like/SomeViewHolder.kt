package com.devnuts.ruflu.ui.adapter.viewholder.like

import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.ItemSomeUserBinding
import com.devnuts.ruflu.ui.adapter.viewholder.ModelViewHolder
import com.devnuts.ruflu.ui.model.home.ImageUIModel
import com.devnuts.ruflu.ui.model.home.UserUIModel
import com.devnuts.ruflu.util.UserUtil
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class SomeViewHolder(
    private val binding: ItemSomeUserBinding
) : ModelViewHolder<UserUIModel>(binding) {

    override fun bindData(model: UserUIModel) {
        if (model.images.isNotEmpty()) {
            UserUtil.setImageWithPiccaso(
                itemView,
                (model.images[0] as ImageUIModel).image,
                binding.civSome
            )
        }
        else
            binding.civSome.setImageResource(R.drawable.noimg_fac)

        binding.nickName.text = model.nickName
        binding.age.text = model.age
    }

    override fun bindViews(model: UserUIModel, adapterListener: ModelAdapterListener?) {
        binding.civSome.setOnClickListener {
            adapterListener?.onClick(it, model, absoluteAdapterPosition)
        }
    }
}