package com.devnuts.ruflu.util.listener

import android.view.View
import com.devnuts.ruflu.ui.model.Model

interface ModelAdapterListener {
    fun onClick(view: View, model: Model, position: Int)
}