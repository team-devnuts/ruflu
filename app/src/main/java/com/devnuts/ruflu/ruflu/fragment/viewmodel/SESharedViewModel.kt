package com.devnuts.ruflu.ruflu.fragment.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.ui.model.home.UserDtl

class SESharedViewModel : ViewModel() {

    private val TAG = javaClass.name
    private var _lv1UserDtl: MutableLiveData<UserDtl> = MutableLiveData()
    val userDtl get() = _lv1UserDtl

    fun setUserDtl(userDtl: UserDtl) {
        _lv1UserDtl.value = userDtl
    }

    fun detachUser() { _lv1UserDtl.value = null }

}