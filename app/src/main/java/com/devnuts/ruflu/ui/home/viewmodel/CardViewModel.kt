package com.devnuts.ruflu.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devnuts.ruflu.domain.entities.UserEntity
import com.devnuts.ruflu.domain.entities.toUiModel
import com.devnuts.ruflu.domain.repository.HomeRepository
import com.devnuts.ruflu.domain.usecase.AddUserInMyHateListUseCase
import com.devnuts.ruflu.domain.usecase.AddUserInMyLikeListUseCase
import com.devnuts.ruflu.domain.usecase.GetUserListUseCase
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val addUserInMyHateListUseCase: AddUserInMyHateListUseCase,
    private val addUserInMyLikeListUseCase: AddUserInMyLikeListUseCase
) : ViewModel() {

    private val _userInfo = MutableStateFlow<List<UserUIModel>>(emptyList())
    val userInfo = _userInfo.asStateFlow()

    fun loadUserCard() = viewModelScope.launch {
        getUserListUseCase()
            .onSuccess {
                Log.d("flow", "distance : ${it.get(0).distance}")
                _userInfo.value = it.map { entity ->
                    entity.toUiModel(CellType.USER_CARD_CEL)
                }
            }
            .onFailure {
                Log.d("flow", "home failure")
                Log.d("flow", "${it.message}")
            }
    }

    fun addHateUser(position: Int) = viewModelScope.launch{
        val map = HashMap<String, String>()
        map["to_user_id"] = _userInfo.value[position].userId

        addUserInMyHateListUseCase(map)
            .onSuccess {
                Log.d("flow", "message : ${it.message}, code : ${it.code}")
            }
            .onFailure {
                Log.d("flow", "message : ${it.message}")
            }
    }

    fun addLikeUser(position: Int) = viewModelScope.launch {
        val map = HashMap<String, String>()
        map["other_user_id"] = _userInfo.value[position].userId

        addUserInMyLikeListUseCase(map)
            .onSuccess {
                // UI state 최신화
                Log.d("flow", "message : ${it.message}, code : ${it.code}")
            }
            .onFailure {
                Log.d("flow", "message : ${it.message}")
            }
    }
}
