package com.devnuts.ruflu.ui.onboarding.fragment.six

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnboardingJobViewModel : ViewModel() {
    private var _job =  MutableLiveData<String>()
    val job: LiveData<String> get() = _job

    fun changeEditText(text: String) {
        _job.value = text
    }
}