package com.devnuts.ruflu.ui.onboarding.fragment.three

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnboardingNickNameViewModel : ViewModel() {
    private var _nickName =  MutableLiveData<String>()
    val nickName: LiveData<String> get() = _nickName

    fun changeEditText(text: String) {
        _nickName.value = text
    }
}