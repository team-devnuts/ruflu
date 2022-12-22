package com.devnuts.ruflu.ui.like.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.domain.repository.LikeRepository
import com.devnuts.ruflu.ui.model.home.UserDtl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class LikeSubSEViewModel : ViewModel() {
    private val likeRepository = LikeRepository()

    private val _lv1User by lazy {
        MutableLiveData<ArrayList<UserDtl>>().also {
            loadSeLv1User()
        }
    }
    val lv1User: MutableLiveData<ArrayList<UserDtl>> get() = _lv1User

    private val _lv2User by lazy {
        MutableLiveData<ArrayList<UserDtl>>().also {
            loadSeLv2User()
        }
    }
    val lv2User: MutableLiveData<ArrayList<UserDtl>> get() = _lv2User

    private fun loadSeLv1User() {
        val call = likeRepository.getSeLv1User()

        call.enqueue(object : Callback<List<UserDtl>> {
            override fun onResponse(call: Call<List<UserDtl>>, response: Response<List<UserDtl>>) {
                if (response.isSuccessful) {
                    Timber.d("callback success")
                    val nbUserList: List<UserDtl>? = response.body()
                    _lv1User.value =
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
        val call = likeRepository.getSeLv2User()

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
        val call = likeRepository.insertSeLikeLv2(userId)
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

    fun removeLikeUser(user: UserDtl?) {
        val userId = user?.user_id
        val call = likeRepository.deleteLikeUser(userId)

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

    val getSeLikeLv1User = { pos: Int -> _lv1User.value?.get(pos) }

    val getSeLikeLv2User = { pos: Int -> _lv2User.value?.get(pos) }
}
