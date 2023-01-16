package com.devnuts.ruflu.ui.onboarding.fragment.one

import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class OnboardingGenderViewModel : ViewModel() {

    private val _gender = MutableLiveData<Int>()
    val gender: LiveData<Int> get() = _gender

    fun genderSingleChoice(rv: RecyclerView, position: Int) {
        val view = rv.layoutManager?.findViewByPosition(position)

        view?.isSelected = true
        _gender.value = position

        for (i in 0 .. rv.childCount) {
            val otherView = rv.layoutManager?.findViewByPosition(i)
            if (otherView != view) otherView?.isSelected = false
        }
    }
}
