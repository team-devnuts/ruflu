package com.devnuts.ruflu.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.ui.adapter.viewholder.ModelViewHolder
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.util.listener.ModelAdapterListener
import com.devnuts.ruflu.util.mapper.ModelViewHolderMapper

open class ModelRecyclerViewAdapter<M: Model>(
    private val modelAdapterListener: ModelAdapterListener? = null
): RecyclerView.Adapter<ModelViewHolder<M>>() {
    private var modelList: MutableList<Model> = mutableListOf()

    override fun getItemCount() = modelList.size

    override fun getItemViewType(position: Int) = modelList[position].type.ordinal

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder<M> {
        return ModelViewHolderMapper.map(parent, CellType.values()[viewType], modelAdapterListener)
    }

    override fun onBindViewHolder(holder: ModelViewHolder<M>, position: Int) {
        val model = modelList[position] as M
        holder.bindData(model)
        holder.bindViews(model, modelAdapterListener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Model>?) {
        newList?.let { modelList = newList.toMutableList() }
        notifyDataSetChanged()
    }

    fun updateModelAtPosition(model: Model, position: Int) {
        if (modelList.size <= position) modelList.add(position, model)
        else modelList[position] = model
        notifyItemChanged(position)
    }

    fun updateModelsAtPosition(list: List<Model>, startIndex: Int, endIndex: Int) {
        modelList = (modelList.subList(0, startIndex) + list + modelList.subList(
            modelList.size - 3,
            modelList.size
        )).toMutableList()
        notifyItemRangeChanged(0,modelList.size-2)
    }

}