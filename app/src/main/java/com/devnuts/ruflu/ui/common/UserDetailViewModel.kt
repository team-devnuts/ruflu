package com.devnuts.ruflu.ui.common

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devnuts.ruflu.domain.entities.UserDetailEntity
import com.devnuts.ruflu.domain.entities.toUiModel
import com.devnuts.ruflu.domain.usecase.GetUserDetailUseCase
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserDetailUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase
) : ViewModel() {

    private var _userDetailInfo = MutableLiveData<UserDetailUIModel>()
    val userDetailInfo: LiveData<UserDetailUIModel> get() = _userDetailInfo

    fun loadUserDetail(userId: String) = viewModelScope.launch {
        getUserDetailUseCase(userId).onSuccess {
            _userDetailInfo.value = it.toUiModel(cellType = CellType.DETAIL_CEL)
        }.onFailure {
            Log.d("flow", "loadUserDetail Failed")
        }
    }
}