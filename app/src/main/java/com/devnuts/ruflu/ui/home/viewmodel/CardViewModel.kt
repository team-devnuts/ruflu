package com.devnuts.ruflu.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devnuts.ruflu.domain.entities.UserEntity
import com.devnuts.ruflu.domain.entities.toUiModel
import com.devnuts.ruflu.domain.repository.HomeRepository
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
    private val getUserListUseCase: GetUserListUseCase
) : ViewModel() {

//    private val _userCard by lazy {
//        MutableLiveData<List<UserUIModel>>().also {
//            loadUserCard()
//        }
//    }
//    val userCard: MutableLiveData<List<UserUIModel>> get() = _userCard

    private val _userInfo = MutableStateFlow<List<UserUIModel>>(emptyList())
    val userInfo = _userInfo.asStateFlow()

    fun loadUserCard() = viewModelScope.launch {
        getUserListUseCase()
            .onSuccess {
                _userInfo.value = it.map { entity ->
                    entity.toUiModel(CellType.USER_CARD_CEL)
                }
            }
            .onFailure {
                Log.d("flow", "failure")
            }
    }

//    fun addHateUser(position: Int) {
//        val map = HashMap<String, String>()
//        map["to_user_id"] = _userInfo.value[position].userId
//        val call = homeRepository.addUserInMyHateList(map)
//
//        call.enqueue(object : Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                if (response.isSuccessful) {
//                    Timber.d("callback success")
//                }
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Timber.tag("callback fail").e(t)
//            }
//        })
//    }
//
//    fun addLikeUser(position: Int) {
//        val map = HashMap<String, String>()
//        map["to_user_id"] = _userInfo.value[position].userId
//
//        val call = homeRepository.addUserInMyLikeList(map)
//        call.enqueue(object : Callback<String> {
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                if (response.isSuccessful) {
//                    Timber.d("callback success")
//                    // 결과값에 따라서 매치되었다고 화면 표시
//                }
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Timber.tag("callback fail").e(t)
//            }
//        })
//    }
}
