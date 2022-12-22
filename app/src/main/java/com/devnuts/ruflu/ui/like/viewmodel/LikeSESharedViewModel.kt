package com.devnuts.ruflu.ui.like.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.ui.model.home.UserDtl

class LikeSESharedViewModel : ViewModel() {

    private var _lv1UserDtl: MutableLiveData<UserDtl> = MutableLiveData()
    val userDtl get() = _lv1UserDtl

    fun setUserDtl(userDtl: UserDtl) {
        _lv1UserDtl.value = userDtl
    }

    fun detachUser() { _lv1UserDtl.value = null }
}
