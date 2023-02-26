package com.devnuts.ruflu.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devnuts.ruflu.domain.entities.toUiModel
import com.devnuts.ruflu.domain.usecase.AddUserInMyHateListUseCase
import com.devnuts.ruflu.domain.usecase.AddUserInMyLikeListUseCase
import com.devnuts.ruflu.domain.usecase.GetUserListUseCase
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val getUserListUseCase: GetUserListUseCase,
    private val addUserInMyHateListUseCase: AddUserInMyHateListUseCase,
    private val addUserInMyLikeListUseCase: AddUserInMyLikeListUseCase
) : ViewModel() {

    private val _cardUiState = MutableStateFlow<List<UserUIModel>>(emptyList())
    val cardUiState = _cardUiState.asStateFlow()

    fun getUserCard() = viewModelScope.launch {
        getUserListUseCase()
            .onSuccess {
                _cardUiState.value = it.map { entity ->
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
        map["to_user_id"] = _cardUiState.value[position].userId

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
        map["other_user_id"] = _cardUiState.value[position].userId

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
