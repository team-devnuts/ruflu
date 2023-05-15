package com.devnuts.ruflu.ui.onboarding.fragment.one

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class OnboardingGenderViewModel : ViewModel() {
    private val _gender = MutableLiveData<Int>()
    val gender: LiveData<Int> get() = _gender
    private var size = 0
    fun genderSingleChoice(rv: RecyclerView, position: Int) {
        if (size == 0) size = rv.childCount

        val view = rv.layoutManager?.findViewByPosition(position)
        view?.isSelected = true
        _gender.value = position

        for (i in 0 .. size) {
            val otherView = rv.layoutManager?.findViewByPosition(i)
            if (otherView != view) otherView?.isSelected = false
        }
    }
}
