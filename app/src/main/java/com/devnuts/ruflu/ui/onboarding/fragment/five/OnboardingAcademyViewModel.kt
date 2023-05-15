package com.devnuts.ruflu.ui.onboarding.fragment.five

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.ui.model.Model
import com.devnuts.ruflu.ui.model.onboarding.AcademyUIModel

class OnboardingAcademyViewModel : ViewModel() {
    val academyInfo = listOf<String>("고등학교", "대학교", "대학원", "박사")
    private var _academy = MutableLiveData<Int>()
    val academy: LiveData<Int> get() = _academy

    fun initAcademy(position: Int): List<Model> {
        _academy.value = position

        val academyRange = mutableListOf<AcademyUIModel>()
        for (i in academyInfo.indices) {
            academyRange.add(AcademyUIModel(academy = academyInfo[i], isSelected = false),)
            if (position == i) academyRange[position].isSelected = true
        }
        return academyRange
    }
}