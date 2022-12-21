package com.devnuts.ruflu.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.domain.repository.HomeRepository
import com.devnuts.ruflu.ui.model.home.UserDtl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeSubNBViewModel : ViewModel() {

    private val TAG = javaClass.name
    private val homeRepository = HomeRepository()

    private val _nbUser by lazy {
        MutableLiveData<ArrayList<UserDtl>>().also {
            init()
        }
    }
    val nbUser: MutableLiveData<ArrayList<UserDtl>> get() = _nbUser

    private fun init() {
        loadNBUser()
    }
    fun loadNBUser() {
        val call = homeRepository.getNBUserList()

        call.enqueue(object : Callback<List<UserDtl>> {
            override fun onResponse(call: Call<List<UserDtl>>, response: Response<List<UserDtl>>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "callback success!!!")
                    val nbUserList: List<UserDtl>? = response.body()
                    nbUser.value = if (nbUserList != null) nbUserList as ArrayList<UserDtl> else ArrayList<UserDtl>()
                }
            }

            override fun onFailure(call: Call<List<UserDtl>>, t: Throwable) {
                Log.d(TAG, ":: callback fail ::")
                Log.d(TAG + ".loadNBUser", "" + t.message)
            }
        })
    }

    fun changeRatingToUser(rating: Float, toUser: UserDtl) {
        val call = homeRepository.changeRatingToUser(rating, toUser.user_id)
    }

    val getNBUser = { pos: Int -> _nbUser.value?.get(pos) }
}
