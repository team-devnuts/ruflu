package com.devnuts.ruflu.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.data.api.response.card.toCardUIModel
import com.devnuts.ruflu.domain.repository.HomeRepository
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _userCard by lazy {
        MutableLiveData<List<UserUIModel>>().also {
            loadUserCard()
        }
    }

    val userCard: MutableLiveData<List<UserUIModel>> get() = _userCard

    fun loadUserCard() {
        val call = homeRepository.getUsers()

        call.enqueue(object : Callback<List<UserModel>> {
            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            ) {
                if (response.isSuccessful) {

                    Timber.d("callback success")
                    val cards: List<UserModel>? = response.body()
                    userCard.value = cards?.map {
                        it.toCardUIModel(CellType.USER_CARD_CEL)
                    }
                }else {
                    Log.d("flow", "$response.message()")
                }
            }
            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                Timber.tag("callback fail").e(t)
            }
        })
    }

    fun addHateUser(position: Int) {
        val map = HashMap<String, String>()
        map["to_user_id"] = _userCard.value!![position].userId
        val call = homeRepository.addHateUser(map)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Timber.tag("callback fail").e(t)
            }
        })
    }

    fun addLikeUser(position: Int) {
        val map = HashMap<String, String>()
        map["to_user_id"] = _userCard.value!![position].userId

        val call = homeRepository.addLikeUser(map)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                    // 결과값에 따라서 매치되었다고 화면 표시
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Timber.tag("callback fail").e(t)
            }
        })
    }
}
