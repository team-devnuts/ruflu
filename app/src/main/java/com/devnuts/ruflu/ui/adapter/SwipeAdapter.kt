package com.devnuts.ruflu.ui.adapter

import com.devnuts.ruflu.ui.some.SomeTouchHelperCallback
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.util.listener.ModelAdapterListener

class SwipeAdapter<M: Model>(
    private val listener: ModelAdapterListener,
) : ModelRecyclerViewAdapter<M>(listener),
    SomeTouchHelperCallback.OnItemMoveListener {

    override fun onItemMove(fromPosition: Int, toPosition: Int) {}

    override fun onItemsWipe(position: Int, direction: Int) {
        listener.onSwipe(position, direction)
        notifyItemRemoved(position)
    }
}
