package com.devnuts.ruflu.ui.signin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class AccountRestoreViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String>
            get() = _email

    init {
        _email.value = ""
    }

    // email 로직
    fun validateEmail(email: String): Boolean {
        val pattern: Pattern = android.util.Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}
