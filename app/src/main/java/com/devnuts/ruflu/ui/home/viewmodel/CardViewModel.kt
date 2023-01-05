package com.devnuts.ruflu.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.data.api.response.card.CardModel
import com.devnuts.ruflu.data.api.response.card.toHomeMenuModel
import com.devnuts.ruflu.domain.repository.HomeRepository
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.CardUIModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class CardViewModel : ViewModel() {
    private val repository: HomeRepository = HomeRepository()

    private val _userCard by lazy {
        MutableLiveData<List<CardUIModel>>().also {
            loadUserCard()
        }
    }
    val userCard: MutableLiveData<List<CardUIModel>> get() = _userCard

    fun loadUserCard() {
        val call = repository.getUserCardList()
        call.enqueue(object : Callback<List<CardModel>> {
            override fun onResponse(
                call: Call<List<CardModel>>,
                response: Response<List<CardModel>>
            ) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                    val cards: List<CardModel>? = response.body()
                    userCard.value = cards?.map {
                        it.toHomeMenuModel(CellType.USER_CARD_CEL)
                    }
                }
            }

            override fun onFailure(call: Call<List<CardModel>>, t: Throwable) {
                Timber.tag("callback fail").e(t)
            }
        })
    }

    fun hateYourUserCard(position: Int) {
        val map = HashMap<String, String>()
        map["to_user_id"] = _userCard.value!![position].user_id
        val call = repository.insertHateUserCard(map)

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

    fun likeYourUserCard(position: Int) {
        val map = HashMap<String, String>()
        map["to_user_id"] = _userCard.value!![position].user_id

        val call = repository.insertLikeUserCard(map)
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
