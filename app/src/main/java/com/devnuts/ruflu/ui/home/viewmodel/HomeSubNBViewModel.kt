package com.devnuts.ruflu.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.domain.repository.HomeRepository
import com.devnuts.ruflu.ui.model.home.UserDtl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeSubNBViewModel : ViewModel() {
    private val homeRepository = HomeRepository()

    private val _nbUser by lazy {
        MutableLiveData<ArrayList<UserDtl>>().also {
            loadNBUser()
        }
    }
    val nbUser: MutableLiveData<ArrayList<UserDtl>> get() = _nbUser

    private fun loadNBUser() {
        val call = homeRepository.getNBUserList()

        call.enqueue(object : Callback<List<UserDtl>> {
            override fun onResponse(call: Call<List<UserDtl>>, response: Response<List<UserDtl>>) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                    val nbUserList: List<UserDtl>? = response.body()
                    nbUser.value =
                        if (nbUserList != null) nbUserList as ArrayList<UserDtl> else ArrayList<UserDtl>()
                }
            }

            override fun onFailure(call: Call<List<UserDtl>>, t: Throwable) {
                Timber.tag(":: callback fail ::").e(t)
            }
        })
    }

    fun changeRatingToUser(rating: Float, toUser: UserDtl) {
        val call = homeRepository.changeRatingToUser(rating, toUser.user_id)
    }

    val getNBUser = { pos: Int -> _nbUser.value?.get(pos) }
}
