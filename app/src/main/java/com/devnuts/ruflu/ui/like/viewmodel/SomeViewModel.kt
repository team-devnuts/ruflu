package com.devnuts.ruflu.ui.like.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.domain.repository.SomeRepository
import com.devnuts.ruflu.ui.model.home.UserDtl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class SomeViewModel : ViewModel() {
    private val repository = SomeRepository()

    private val _someUser by lazy {
        MutableLiveData<ArrayList<UserDtl>>().also {
            loadSomeUser()
        }
    }
    val someUser: MutableLiveData<ArrayList<UserDtl>> get() = _someUser

    private val _lv2User by lazy {
        MutableLiveData<ArrayList<UserDtl>>().also {
            loadSeLv2User()
        }
    }
    val lv2User: MutableLiveData<ArrayList<UserDtl>> get() = _lv2User

    private fun loadSomeUser() {
        val call = repository.getSeLv1User()

        call.enqueue(object : Callback<List<UserDtl>> {
            override fun onResponse(call: Call<List<UserDtl>>, response: Response<List<UserDtl>>) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                    val nbUserList: List<UserDtl>? = response.body()
                    _someUser.value =
                        if (nbUserList != null) nbUserList as ArrayList<UserDtl> else ArrayList()
                } else {
                    Timber.d(response.message())
                }
            }

            override fun onFailure(call: Call<List<UserDtl>>, t: Throwable) {
                Timber.tag(":: callback fail ::").e(t)
            }
        })
    }

    private fun loadSeLv2User() {
        val call = repository.getSeLv2User()

        call.enqueue(object : Callback<List<UserDtl>> {
            override fun onResponse(call: Call<List<UserDtl>>, response: Response<List<UserDtl>>) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                    val nbUserList: List<UserDtl>? = response.body()
                    _lv2User.value =
                        if (nbUserList != null) nbUserList as ArrayList<UserDtl> else ArrayList()
                }
            }

            override fun onFailure(call: Call<List<UserDtl>>, t: Throwable) {
                Timber.tag(":: callback fail ::").e(t)
            }
        })
    }

    fun insertSeLikeLv2(userId: String) {
        val call = repository.insertSeLikeLv2(userId)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                    val message = response.body()
                    // _lv2User.value = if (nbUserList != null) nbUserList as ArrayList<LikeLv2User> else ArrayList()
                    // 매칭시 알림화면
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Timber.tag(":: callback fail ::").e(t)
            }
        })
    }

    fun removeSomeUser(user: UserDtl?) {
        val userId = user?.user_id
        val call = repository.deleteLikeUser(userId)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                    val message = response.body()
                    // _lv2User.value = if (nbUserList != null) nbUserList as ArrayList<LikeLv2User> else ArrayList()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Timber.tag(":: callback fail ::").e(t)
            }
        })
    }

    fun sendMessageAskingTalkToUser(user: UserDtl?) {}

    val getSomeUser = { pos: Int -> _someUser.value?.get(pos) }

    val getSeLikeLv2User = { pos: Int -> _lv2User.value?.get(pos) }
}
