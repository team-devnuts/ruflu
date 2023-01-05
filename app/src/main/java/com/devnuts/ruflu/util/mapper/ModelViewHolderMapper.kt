package com.devnuts.ruflu.util.mapper

import android.view.LayoutInflater
import android.view.ViewGroup
import com.devnuts.ruflu.databinding.ItemUserCardBinding
import com.devnuts.ruflu.databinding.ItemUserCardImagesBinding
import com.devnuts.ruflu.ui.adapter.viewholder.CardImageViewHolder
import com.devnuts.ruflu.ui.adapter.viewholder.CardViewHolder
import com.devnuts.ruflu.ui.adapter.viewholder.ModelViewHolder
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.util.listener.ModelAdapterListener

object ModelViewHolderMapper {
    @Suppress("UNCHECKED_CAST")
    fun <M : Model> map(
        parent: ViewGroup,
        type: CellType,
        adapterListener: ModelAdapterListener? = null
    ): ModelViewHolder<M> {
        val inflater = LayoutInflater.from(parent.context)
        val viewHolder = when (type) {
            CellType.USER_CARD_CEL -> CardViewHolder(
                ItemUserCardBinding.inflate(inflater, parent, false)
            )

            CellType.USER_CARD_IMAGE_CEL -> CardImageViewHolder(
                ItemUserCardImagesBinding.inflate(inflater, parent, false)
            )
        }
        return viewHolder as ModelViewHolder<M>
    }
}