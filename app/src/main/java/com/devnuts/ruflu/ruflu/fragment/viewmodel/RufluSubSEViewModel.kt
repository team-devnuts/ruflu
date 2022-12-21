package com.devnuts.ruflu.ruflu.fragment.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.ruflu.repository.RufluRepository
import com.devnuts.ruflu.ui.model.home.UserDtl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RufluSubSEViewModel : ViewModel() {

    private val TAG = javaClass.name
    private val rufluRepository = RufluRepository()
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

    private fun init() {
    }

    fun loadSeLv1User() {
        val call = rufluRepository.getSeLv1User()

        call.enqueue(object : Callback<List<UserDtl>> {
            override fun onResponse(call: Call<List<UserDtl>>, response: Response<List<UserDtl>>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "callback success")
                    val nbUserList: List<UserDtl>? = response.body()
                    _lv1User.value = if (nbUserList != null) nbUserList as ArrayList<UserDtl> else ArrayList()
                } else {
                    Log.d(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<List<UserDtl>>, t: Throwable) {
                Log.d(TAG, ":: callback fail ::")
                Log.d(TAG, "Error Message" + t.message)
            }
        })
    }

    fun loadSeLv2User() {
        val call = rufluRepository.getSeLv2User()

        call.enqueue(object : Callback<List<UserDtl>> {
            override fun onResponse(call: Call<List<UserDtl>>, response: Response<List<UserDtl>>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "callback success")
                    val nbUserList: List<UserDtl>? = response.body()
                    _lv2User.value = if (nbUserList != null) nbUserList as ArrayList<UserDtl> else ArrayList()
                }
            }

            override fun onFailure(call: Call<List<UserDtl>>, t: Throwable) {
                Log.d(TAG, ":: callback fail ::")
                Log.d(TAG, "Error Message" + t.message)
            }
        })
    }

    fun insertSeLikeLv2(userId: String) {
        val call = rufluRepository.insertSeLikeLv2(userId)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "callback success")
                    val message = response.body()
                    // _lv2User.value = if (nbUserList != null) nbUserList as ArrayList<LikeLv2User> else ArrayList()
                    // 매칭시 알림화면
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, ":: callback fail ::")
                Log.d(TAG, "Error Message" + t.message)
            }
        })
    }

    fun removeLikeUser(user: UserDtl?) {
        val userId = user?.user_id
        val call = rufluRepository.deleteLikeUser(userId)

        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "callback success")
                    val message = response.body()
                    // _lv2User.value = if (nbUserList != null) nbUserList as ArrayList<LikeLv2User> else ArrayList()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, ":: callback fail ::")
                Log.d(TAG, "Error Message" + t.message)
            }
        })
    }

    fun sendMessageAskingTalkToUser(user: UserDtl?) {
    }

    val getSeLikeLv1User = { pos: Int -> _lv1User.value?.get(pos) }

    val getSeLikeLv2User = { pos: Int -> _lv2User.value?.get(pos) }
}
