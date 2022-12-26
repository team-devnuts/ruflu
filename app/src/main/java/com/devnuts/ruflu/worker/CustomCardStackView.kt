package com.devnuts.ruflu.worker

import android.content.Context
import android.util.AttributeSet
import android.view.DragEvent
import android.view.MotionEvent
import com.yuyakaido.android.cardstackview.*

/*
    Ruflu Customized CardStackView
   -Direction 과 swipe 제어를 위해 커스터마이징
 */
class CustomCardStackView(context: Context?, attr: AttributeSet?) : CardStackView(context, attr) {

    private lateinit var onCustomSwipeTouchEvent: OnCustomSwipeTouchEvent

    interface OnCustomSwipeTouchEvent {
        fun onCustomizedSwipe(event: MotionEvent?)
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        onCustomSwipeTouchEvent.onCustomizedSwipe(event)
        return super.onInterceptTouchEvent(event)
    }

    fun setOnCustomSwipeTouchEvent(onCustomSwipeTouchEvent: OnCustomSwipeTouchEvent) {
        this.onCustomSwipeTouchEvent = onCustomSwipeTouchEvent
    }

    override fun onDragEvent(event: DragEvent?): Boolean {
        return super.onDragEvent(event)
    }
}
