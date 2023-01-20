package com.devnuts.ruflu.ui.adapter.viewholder.like

import android.util.Log
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.ItemSomeUserBinding
import com.devnuts.ruflu.ui.adapter.viewholder.ModelViewHolder
import com.devnuts.ruflu.ui.model.home.UserImageUIModel
import com.devnuts.ruflu.ui.model.home.UserUIModel
import com.devnuts.ruflu.util.UserUtil
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class SomeViewHolder(
    private val binding: ItemSomeUserBinding
) : ModelViewHolder<UserUIModel>(binding) {

    override fun bindData(model: UserUIModel) {
        if (model.images.isNotEmpty()) {
            UserUtil.setImageWithGlide(
                itemView,
                (model.images[0] as UserImageUIModel).image,
                binding.civSome
            )
        }
        else
            binding.civSome.setImageResource(R.drawable.noimg_fac)

        binding.nickName.text = model.nickName
        binding.age.text = "${UserUtil.getAge(model.birth)}"
    }

    override fun bindViews(model: UserUIModel, adapterListener: ModelAdapterListener?) {
        binding.civSome.setOnClickListener {
            adapterListener?.onClick(it, model, absoluteAdapterPosition)
        }
    }
}