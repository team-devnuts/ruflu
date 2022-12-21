package com.devnuts.ruflu.home

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.animation.AccelerateInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.yuyakaido.android.cardstackview.*
/* Ruflu Customized CardSatckView
   - Direction과 swipe 제어를 위해 커스터마이징

 */
class CustomCardStackView : CardStackView {

    private lateinit var onCustomSwipeTouchEvent : OnCustomSwipeTouchEvent

    constructor(context: Context?, attr: AttributeSet?) : super(context, attr) {}
    interface OnCustomSwipeTouchEvent {
        fun onCustomizedSwipe(event: MotionEvent?)
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {

        if(onCustomSwipeTouchEvent != null) {
            onCustomSwipeTouchEvent.onCustomizedSwipe(event)
        }
        return super.onInterceptTouchEvent(event)
    }

    fun setOnCustomSwipeTouchEvent(onCustomSwipeTouchEvent : OnCustomSwipeTouchEvent) {
        this.onCustomSwipeTouchEvent = onCustomSwipeTouchEvent
    }

    override fun onDragEvent(event: DragEvent?): Boolean {
        return super.onDragEvent(event)
    }

}