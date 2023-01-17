package com.devnuts.ruflu.ui.onboarding.fragment.four

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.ui.model.onboarding.HeightUIModel
import com.devnuts.ruflu.ui.onboarding.fragment.two.OnboardingAgeViewModel

class OnboardingHeightViewModel : ViewModel() {

    private var _height = MutableLiveData<Int>()
    val height: LiveData<Int> get() = _height

    fun initHeight(position: Int): List<Model> {
        if (position > 0) _height.value = position

        val heightRange = mutableListOf<HeightUIModel>()
        for (i in MIN..MAX) {
            heightRange.add(HeightUIModel(height = i, isSelected = false))
            if (position == i - MIN) heightRange[position].isSelected = true
        }

        return heightRange
    }

    companion object {
        private const val MIN = 140
        private const val MAX = 200
    }
}