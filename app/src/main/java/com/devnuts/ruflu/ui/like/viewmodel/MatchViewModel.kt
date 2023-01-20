package com.devnuts.ruflu.ui.like.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.domain.repository.SomeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MatchViewModel : ViewModel() {
    private val repository = SomeRepository()

    private val _matchUser by lazy {
        MutableLiveData<List<UserModel>>().also {
            loadMatchUser()
        }
    }
    val matchUser: MutableLiveData<List<UserModel>> get() = _matchUser



    private fun loadMatchUser() {
        val call = repository.getSeLv2User()

        call.enqueue(object : Callback<ArrayList<UserModel>> {
            override fun onResponse(
                call: Call<ArrayList<UserModel>>,
                response: Response<ArrayList<UserModel>>
            ) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                    val nbUserList: ArrayList<UserModel>? = response.body()
                    _matchUser.value =
                        if (nbUserList != null) nbUserList as ArrayList<UserModel> else arrayListOf()
                }
            }

            override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                Timber.tag(":: callback fail ::").e(t)
            }
        })
    }

    val getMatchUser = { pos: Int -> _matchUser.value?.get(pos) }
}
