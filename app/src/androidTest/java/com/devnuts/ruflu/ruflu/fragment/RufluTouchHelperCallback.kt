package com.devnuts.ruflu.ruflu.fragment

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class RufluTouchHelperCallback(private val itemMoveListener : OnItemMoveListener ) : ItemTouchHelper.Callback() {

    interface OnItemMoveListener {
        fun onItemMove(fromposition: Int, toPosition: Int)
        fun onItemsWipe(position : Int, direction: Int)
    }
    /**
     * 어느 방향으로 움직일지에 따라서 flag 받는것을 정의
     * 드래그는 위, 아래 액션이기 때문에 left, right 을 넘겨줌
     */
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val sFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(0, sFlags)
    }

    /**
     * 어느 위치에서 어느 위치로 변경하는지
     */
    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        itemMoveListener.onItemMove(viewHolder.bindingAdapterPosition, target.bindingAdapterPosition)
        return true
    }

    /**
     * 좌우 스와이프
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        itemMoveListener.onItemsWipe(viewHolder.bindingAdapterPosition, direction)
    }
}