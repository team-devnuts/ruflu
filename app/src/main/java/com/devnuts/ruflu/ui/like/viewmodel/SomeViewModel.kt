package com.devnuts.ruflu.ui.like.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.data.api.response.card.toCardUIModel
import com.devnuts.ruflu.domain.repository.SomeRepository
import com.devnuts.ruflu.ui.model.CellType
import com.devnuts.ruflu.ui.model.home.UserUIModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class SomeViewModel : ViewModel() {
    private val repository = SomeRepository()

    private val _someUser by lazy {
        MutableLiveData<List<UserUIModel>>().also {
            loadSomeUser()
        }
    }
    val someUser: MutableLiveData<List<UserUIModel>> get() = _someUser


    private fun loadSomeUser() {
        val call = repository.getLikeMeList()

        call.enqueue(object : Callback<ArrayList<UserModel>> {
            override fun onResponse(
                call: Call<ArrayList<UserModel>>,
                response: Response<ArrayList<UserModel>>
            ) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                    val users: ArrayList<UserModel>? = response.body()
                    _someUser.value =
                        users?.map { user ->
                            user.toCardUIModel(CellType.SOME_LIKE_CEL)
                        }
                } else {
                    Timber.d(response.message())
                }
            }

            override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
                Timber.tag(":: callback fail ::").e(t)
            }
        })
    }


    fun insertMatch(userId: String) {
        val call = repository.insertSeLikeLv2(userId)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                    val message = response.body()
                    // _lv2User.value = if (nbUserList != null) nbUserList as List<LikeLv2User> else List()
                    // 매칭시 알림화면
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Timber.tag(":: callback fail ::").e(t)
            }
        })
    }

    fun removeSomeUser(user: UserModel?) {
        val userId = user?.user_id
        val call = repository.deleteLikeUser(userId)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                    val message = response.body()
                    // _lv2User.value = if (nbUserList != null) nbUserList as List<LikeLv2User> else List()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Timber.tag(":: callback fail ::").e(t)
            }
        })
    }

    fun sendMessageAskingTalkToUser(user: UserModel?) {}

    val getSomeUser = { pos: Int -> _someUser.value?.get(pos) }
}
