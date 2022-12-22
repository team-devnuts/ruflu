package com.devnuts.ruflu.ui.adapter.itemDecoration

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.RelativeLayout
import timber.log.Timber

class SquareRelativeLayout(context: Context, attributeSet: AttributeSet) :
    RelativeLayout(context, attributeSet) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        Timber.d("spec : $width , $height")
        // setMeasuredDimension(width, width)
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}
