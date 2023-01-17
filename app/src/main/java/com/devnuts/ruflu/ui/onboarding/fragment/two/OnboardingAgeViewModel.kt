package com.devnuts.ruflu.ui.onboarding.fragment.two

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.ui.model.onboarding.AgeUIModel

class OnboardingAgeViewModel : ViewModel() {

    private var _age = MutableLiveData<Int>()
    val age: LiveData<Int> get() = _age

    fun initAge(position: Int): List<Model> {
        _age.value = position

        val ageRange = mutableListOf<AgeUIModel>()
        for (i in MIN..MAX) {
            ageRange.add(AgeUIModel(age = i, isSelected = false))
            if (position == i - MIN) ageRange[position].isSelected = true
        }

        return ageRange
    }

    companion object {
        private const val MIN = 20
        private const val MAX = 50
    }
}