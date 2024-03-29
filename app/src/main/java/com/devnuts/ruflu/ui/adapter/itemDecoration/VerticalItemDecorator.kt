package com.devnuts.ruflu.ui.adapter.itemDecoration

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalItemDecorator(private val divHeight : Int, private val context : Context) : RecyclerView.ItemDecoration() {

    @Override
    override fun getItemOffsets(outRect: Rect, view: View, parent : RecyclerView, state : RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        //dp _ px 변환
        val px : Int = dpToPx(context, divHeight)

        outRect.bottom = px
    }

    private fun dpToPx(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }
}