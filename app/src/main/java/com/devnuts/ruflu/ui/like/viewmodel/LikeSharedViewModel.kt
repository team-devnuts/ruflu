//package com.devnuts.ruflu.ui.like.viewmodel
//
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.devnuts.ruflu.ui.model.home.UserDetailUIModel
//
//class LikeSharedViewModel : ViewModel() {
//
//    private var _userDetail: MutableLiveData<UserDetailUIModel> = MutableLiveData()
//    val userDtl get() = _userDetail
//
//    fun setUserDtl(userDtl: UserDetailUIModel) {
//        _userDetail.value = userDtl
//    }
//
//    fun detachUser() { _userDetail.value = null }
//}
