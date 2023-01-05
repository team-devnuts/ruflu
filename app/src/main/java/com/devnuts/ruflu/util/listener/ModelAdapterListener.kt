package com.devnuts.ruflu.util.listener

import android.view.MotionEvent
import android.view.View
import com.devnuts.ruflu.ui.model.Model

interface ModelAdapterListener {
    fun onClick(view: View, model: Model, position: Int)
    fun onTouch(view: View, model: Model, event: MotionEvent)
}