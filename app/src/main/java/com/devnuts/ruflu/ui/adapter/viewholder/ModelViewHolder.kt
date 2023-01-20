package com.devnuts.ruflu.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.util.listener.ModelAdapterListener

abstract class ModelViewHolder<M : Model>(
    binding: ViewBinding
): RecyclerView.ViewHolder(binding.root) {
    open fun bindData(model: M) {}
    open fun bindViews(
        model: M,
        adapterListener: ModelAdapterListener?
    ) {
    }
    open fun bindDataWithPayLoads(
        model: M,
        payload: MutableList<Any>,
    ){

    }
}