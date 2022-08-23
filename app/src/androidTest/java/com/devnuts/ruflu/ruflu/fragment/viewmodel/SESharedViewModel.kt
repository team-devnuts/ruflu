package com.devnuts.ruflu.ruflu.fragment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.home.model.UserDtl
import com.devnuts.ruflu.ruflu.fragment.model.LikeLv1User
import com.devnuts.ruflu.ruflu.repository.RufluRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SESharedViewModel : ViewModel() {

    private val TAG = javaClass.name
    private var _lv1UserDtl: MutableLiveData<UserDtl> = MutableLiveData()
    val userDtl get() = _lv1UserDtl

    fun setUserDtl(userDtl: UserDtl) {
        _lv1UserDtl.value = userDtl
    }

    fun detachUser() { _lv1UserDtl.value = null }

}