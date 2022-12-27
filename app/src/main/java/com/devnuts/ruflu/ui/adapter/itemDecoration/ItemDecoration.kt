package com.devnuts.ruflu.ui.adapter.itemDecoration

import android.app.Activity
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates
import timber.log.Timber

class ItemDecoration(private val pActivity: Activity) : RecyclerView.ItemDecoration() {

    private var spanCount by Delegates.notNull<Int>()
    private var spacing by Delegates.notNull<Int>()
    private var outerMargin by Delegates.notNull<Int>()

    init {
        spanCount = 3
        spacing = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            12F,
            pActivity.resources.displayMetrics
        ).toInt()
        outerMargin = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            50F,
            pActivity.resources.displayMetrics
        ).toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val maxCount = parent.adapter!!.itemCount
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        val row = position / spanCount
        val lastRow = (maxCount - 1) / spanCount

        Timber.tag("ItemDeco").d("$row = $position / $spanCount")
        Timber.tag("ItemDeco").d("outerMargin : $outerMargin")

        outRect.left = column * spacing / spanCount
        outRect.right = spacing - (column + 1) * spacing / spanCount
        outRect.top = spacing * 2

        if (row == lastRow) {
            outRect.bottom = outerMargin
        }
    }
}
