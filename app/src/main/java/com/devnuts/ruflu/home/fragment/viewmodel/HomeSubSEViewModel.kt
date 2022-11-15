package com.devnuts.ruflu.home.fragment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.home.fragment.HomeSubSEFrag
import com.devnuts.ruflu.home.model.UserCard
import com.devnuts.ruflu.home.repository.HomeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeSubSEViewModel(application: Application) : AndroidViewModel(application){

    private val homeRepository: HomeRepository = HomeRepository()
    private val TAG = javaClass.name

    private val _userCard by lazy {
        MutableLiveData<ArrayList<UserCard>>().also {
            init()
        }
    }

    val userCard: MutableLiveData<ArrayList<UserCard>> get() = _userCard

    private fun init() {
        loadUserCard()
    }

    fun loadUserCard() {
        val call = homeRepository.getUserCardList()
        call.enqueue(object : Callback<List<UserCard>> {
            override fun onResponse(call: Call<List<UserCard>>, response: Response<List<UserCard>>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "Success")
                    val userCardList: List<UserCard>? = response.body()
                    userCard.value = userCardList as ArrayList<UserCard>?
                }
            }

            override fun onFailure(call: Call<List<UserCard>>, t: Throwable) {
                Log.d(TAG, "callback fail")
                Log.d(TAG + ".selectUserCardList", "${t.message}, ${t.printStackTrace()}")
            }
        })
    }

    fun hateYourUserCard(position: Int) {
        val map = HashMap<String, String>()
        map.put("to_user_id", _userCard.value!!.get(position).user_id)
        val call = homeRepository.insertHateUserCard(map)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "callback success!!!")

                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, "callback fail")
                Log.d(TAG + ".hateYourUserCard", "" + t.message)
            }

        })
    }

    fun likeYourUserCard(position: Int) {
        val map = HashMap<String, String>()
        map.put("to_user_id", _userCard.value!!.get(position).user_id)

        val call = homeRepository.insertLikeUserCard(map)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "callback success!!!")
                    // 결과값에 따라서 매치되었다고 화면 표시
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, "callback fail")
                Log.d(TAG + ".likeYourUserCard", "" + t.stackTrace)
            }

        })
    }
}

