package com.devnuts.ruflu.ui.some.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devnuts.ruflu.domain.entities.toUiModel
import com.devnuts.ruflu.domain.usecase.GetUserMatchedWithMeListUseCase
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val getUserMatchedWithMeListUseCase: GetUserMatchedWithMeListUseCase
) : ViewModel() {

    private val _matchInfo = MutableStateFlow<List<UserUIModel>>(emptyList())
    val matchInfo = _matchInfo.asStateFlow()

    fun getUserMatchedWithMeList() = viewModelScope.launch {
        getUserMatchedWithMeListUseCase().onSuccess {
                _matchInfo.value = it.map { entity ->
                    entity.toUiModel(CellType.SOME_MATCH_CEL)
                }
            }
            .onFailure {
                Log.d("flow", "match failure")
                Log.d("flow", "${it.message}")
            }
    }
    val getMatchUser = { pos: Int -> _matchInfo.value[pos] }
}
