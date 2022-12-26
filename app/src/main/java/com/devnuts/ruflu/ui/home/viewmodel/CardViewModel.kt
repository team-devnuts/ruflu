package com.devnuts.ruflu.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.domain.repository.HomeRepository
import com.devnuts.ruflu.ui.model.home.UserCard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class CardViewModel : ViewModel() {
    private val repository: HomeRepository = HomeRepository()

    private val _userCard by lazy {
        MutableLiveData<ArrayList<UserCard>>().also {
            loadUserCard()
        }
    }
    val userCard: MutableLiveData<ArrayList<UserCard>> get() = _userCard

    fun loadUserCard() {
        val call = repository.getUserCardList()
        call.enqueue(object : Callback<List<UserCard>> {
            override fun onResponse(
                call: Call<List<UserCard>>,
                response: Response<List<UserCard>>
            ) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                    val userCardList: List<UserCard>? = response.body()
                    userCard.value = userCardList as ArrayList<UserCard>?
                }
            }

            override fun onFailure(call: Call<List<UserCard>>, t: Throwable) {
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
