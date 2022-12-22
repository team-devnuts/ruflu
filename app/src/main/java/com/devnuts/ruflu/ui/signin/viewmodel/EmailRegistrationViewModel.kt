package com.devnuts.ruflu.ui.signin.viewmodel

import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class EmailRegistrationViewModel : ViewModel() {
    // email 로직
    fun validateEmail(email: String): Boolean {
        val pattern: Pattern = android.util.Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}
