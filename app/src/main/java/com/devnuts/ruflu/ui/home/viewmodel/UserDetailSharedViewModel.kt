package com.devnuts.ruflu.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.ui.model.home.UserDtl
import timber.log.Timber

class UserDetailSharedViewModel : ViewModel() {
    private var _userDtl: MutableLiveData<UserDtl> = MutableLiveData()
    val nbUserDtl get() = _userDtl

    fun setUserDtl(userDtl: UserDtl?) {
        Timber.d("$userDtl")
        _userDtl.value = userDtl
    }

    fun detachNBUser() {
        _userDtl.value = null
    }
}
