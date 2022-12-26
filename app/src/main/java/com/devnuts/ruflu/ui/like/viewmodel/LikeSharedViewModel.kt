package com.devnuts.ruflu.ui.like.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.ui.model.home.UserDtl

class LikeSharedViewModel : ViewModel() {

    private var _userDetail: MutableLiveData<UserDtl> = MutableLiveData()
    val userDtl get() = _userDetail

    fun setUserDtl(userDtl: UserDtl) {
        _userDetail.value = userDtl
    }

    fun detachUser() { _userDetail.value = null }
}
