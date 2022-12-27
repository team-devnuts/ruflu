package com.devnuts.ruflu.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.ui.model.home.UserDetailUIModel
import timber.log.Timber

class UserDetailSharedViewModel : ViewModel() {
    private var _userDetail: MutableLiveData<UserDetailUIModel> = MutableLiveData()
    val userDetail get() = _userDetail

    fun setUserDtl(userDtl: UserDetailUIModel?) {
        Timber.d("$userDtl")
        _userDetail.value = userDtl
    }

    fun detachNBUser() {
        _userDetail.value = null
    }
}
