package com.devnuts.ruflu.ui.signin.viewmodel

import android.util.Log
import android.view.KeyEvent
import android.widget.EditText
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devnuts.ruflu.BR
import com.devnuts.ruflu.domain.usecase.SendUserAuthPhoneNumberUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class SMSViewModel @Inject constructor(
    private val sendUserAuthPhoneNumberUserCase: SendUserAuthPhoneNumberUserCase
) : ViewModel() {

    private var _phoneNumber: String? = null
    val phoneNumber get() = _phoneNumber

    private val verifyCode = arrayListOf("", "", "", "")
    val verifyCodeEtList = arrayListOf<EditText>()

    private var _code = MutableLiveData<String>()
    val code get() = _code

    inner class Observer : BaseObservable() {
        @get:Bindable
        var verifyCode1: String? = null
            set(value) {
                field = value
                verifyCode[0] = verifyCode1!!
                notifyPropertyChanged(BR.verifyCode2)
                stringToList()
            }

        @get:Bindable
        var verifyCode2: String? = null
            set(value) {
                field = value
                verifyCode[1] = verifyCode2!!
                notifyPropertyChanged(BR.verifyCode2)
                stringToList()
            }

        @get:Bindable
        var verifyCode3: String? = null
            set(value) {
                field = value
                verifyCode[2] = verifyCode3!!
                notifyPropertyChanged(BR.verifyCode3)
                stringToList()
            }

        @get:Bindable
        var verifyCode4: String? = null
            set(value) {
                field = value
                verifyCode[3] = verifyCode4!!
                notifyPropertyChanged(BR.verifyCode4)
                stringToList()
            }

        fun onKeyListener(editText: EditText) {
            editText.setOnKeyListener { v, keyCode, event ->
                if (event?.action == KeyEvent.ACTION_DOWN) {
                    val loc: Int = verifyCodeEtList.indexOf(editText)

                    if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN) {
                        controlEditTextFocus(loc, BACK)
                    } else {
                        checkIfValueExists(loc, event.number.toString())
                        controlEditTextFocus(loc, NEXT)
                    }
                }
                false
            }
        }
    }

    private fun stringToList() {
        _code.value = verifyCode.joinToString("", "", "")
    }

    fun validatePhoneNumber(phoneNumber: String): Boolean {
        // val pattern : Pattern = android.util.Patterns.PHONE
        // pattern.matcher(phoneNumber).matches()
        val regex =
            "^\\s*(010|011|012|013|014|015|016|017|018|019)(-|\\)|\\s)*(\\d{3,4})(-|\\s)*(\\d{4})\\s*$"
        return Pattern.compile(regex).matcher(phoneNumber).matches()
    }

    private fun checkIfValueExists(loc: Int, num: String) {
        if (verifyCodeEtList[loc].textSize > 0) {
            verifyCodeEtList[loc].setText(num)
        }
    }

    private fun controlEditTextFocus(loc: Int, direction: String) {
        when (direction) {
            BACK -> if (loc != 0) moveCursor(loc - 1)
            NEXT -> {
                if (loc != 3) {
                    moveCursor(loc + 1)

                    // 인증번호 끝일 경우
                } else verifyCodeEtList[loc].selectAll() // 마지막 수 는 requestFocus x
            }
        }
    }

    private fun moveCursor(loc: Int) {
        verifyCodeEtList[loc].selectAll()
        verifyCodeEtList[loc].requestFocus()
    }

    fun setPhoneNumber(phoneNumber: String) {
        _phoneNumber = phoneNumber
    }

    fun sendPhoneNumber() = viewModelScope.launch {
        phoneNumber?.let {
            val response = sendUserAuthPhoneNumberUserCase(it)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Log.d("ho", "${body.result}")
                }
            }else {
                throw Exception("휴대폰 인증번호 받기 실패!!!!!!")
            }
        }
    }

    companion object {
        const val BACK = "left"
        const val NEXT = "right"
    }
}
