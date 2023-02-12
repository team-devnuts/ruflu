package com.devnuts.ruflu.ui.some.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devnuts.ruflu.data.api.response.home.model.UserModel
import com.devnuts.ruflu.domain.entities.toUiModel
import com.devnuts.ruflu.domain.usecase.AddUserInMyMatchListUseCase
import com.devnuts.ruflu.domain.usecase.GetLikeMeListUseCase
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikeViewModel @Inject constructor(
    private val getLikeMeListUseCase: GetLikeMeListUseCase,
    private val addUserInMyMatchListUseCase: AddUserInMyMatchListUseCase,
) : ViewModel() {

    private val _userInfo = MutableStateFlow<List<UserUIModel>>(emptyList())
    val userInfo = _userInfo.asStateFlow()

    fun getLikeMeUserList() = viewModelScope.launch {
        getLikeMeListUseCase()
            .onSuccess {
                _userInfo.value = it.map { entity ->
                    entity.toUiModel(CellType.SOME_LIKE_CEL)
                }
            }
            .onFailure {
                Log.d("flow", "some failure")
                Log.d("flow", "${it.message}")
            }

        Log.d("flow", "과연? ${userInfo.value}")
    }

    fun addUserInMyMatchList(userId: String) = viewModelScope.launch {
        addUserInMyMatchListUseCase(userId)
            .onSuccess {
                Log.d("flow", "message : ${it.message}, code : ${it.code}")
            }
            .onFailure {
                Log.d("flow", "message : ${it.message}")
            }
    }

//    fun removeSomeUser(user: UserModel?) {
//        val userId = user?.user_id
//        val call = repository.deleteLikeUser(userId)
//
//        call.enqueue(object : Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                if (response.isSuccessful) {
//                    Timber.d("callback success")
//                    val message = response.body()
//                    // _lv2User.value = if (nbUserList != null) nbUserList as List<LikeLv2User> else List()
//                }
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Timber.tag(":: callback fail ::").e(t)
//            }
//        })
//    }

    fun sendMessageAskingTalkToUser(user: UserModel?) {}

    val getSomeUser = { pos: Int -> _userInfo.value.get(pos) }
}
