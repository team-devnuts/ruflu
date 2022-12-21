package com.devnuts.ruflu.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.ui.model.home.UserDtl

class UserDtlSharedViewModel : ViewModel() {

    private val TAG = javaClass.name
    private var _userDtl: MutableLiveData<UserDtl> = MutableLiveData()
    val nbUserDtl get() = _userDtl

    fun setUserDtl(userDtl: UserDtl?) {
        Log.d("NBharedViewModel", "$userDtl")
        _userDtl.value = userDtl
    }

    fun detachNBUser() { _userDtl.value = null }
}
