package com.devnuts.ruflu.ui.onboarding.fragment.three

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.RufluApp

class OnboardingNickNameViewModel : ViewModel() {
    private var _nickName =  MutableLiveData<String>()
    val nickName: LiveData<String> get() = _nickName

    fun changeEditText(text: String) {
        _nickName.value = text
    }
}