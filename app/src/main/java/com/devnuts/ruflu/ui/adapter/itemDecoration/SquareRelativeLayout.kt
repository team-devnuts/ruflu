package com.devnuts.ruflu.ui.adapter.itemDecoration

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.RelativeLayout

class SquareRelativeLayout(context: Context, attributeSet: AttributeSet) : RelativeLayout(context, attributeSet) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var width = MeasureSpec.getSize(widthMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)
        Log.d("Square", "spec : $width , $height")
        //setMeasuredDimension(width, width)
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}