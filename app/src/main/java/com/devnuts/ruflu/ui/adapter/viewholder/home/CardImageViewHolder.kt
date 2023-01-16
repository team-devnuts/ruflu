package com.devnuts.ruflu.ui.adapter.viewholder

import android.annotation.SuppressLint
import android.graphics.Outline
import android.os.Build
import android.view.View
import android.view.ViewOutlineProvider
import com.devnuts.ruflu.R
import com.devnuts.ruflu.databinding.ItemUserCardImagesBinding
import com.devnuts.ruflu.ui.model.home.UserImageUIModel
import com.devnuts.ruflu.util.UserUtil
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class CardImageViewHolder(
    private val binding: ItemUserCardImagesBinding
) : ModelViewHolder<UserImageUIModel>(binding) {

    override fun bindData(model: UserImageUIModel) {}

    @SuppressLint("ObsoleteSdkInt")
    override fun bindViews(model: UserImageUIModel, adapterListener: ModelAdapterListener?) {
        binding.root.setBackgroundResource(R.drawable.user_card_style)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val outlineProvider = object : ViewOutlineProvider() {
                override fun getOutline(view: View?, outline: Outline?) {
                    outline!!.setRoundRect(0, 0, view!!.width, view.height, 20f)
                }
            }
            binding.ivCardImage.outlineProvider = outlineProvider
            binding.ivCardImage.clipToOutline = true
        }

        UserUtil.setImageWithPiccaso(binding.root, model.image, binding.ivCardImage)
    }
}