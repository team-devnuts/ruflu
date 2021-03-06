package com.devnuts.ruflu

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devnuts.ruflu.mmodel.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val repository = MainRepository()
    private val TAG = javaClass.name

    private val _users : MutableLiveData<User> by lazy {
        MutableLiveData<User>().also {
            it.value = loadUsers()
        }
    }

    val user : LiveData<User> get() = _users

    private fun loadUsers(): User {
        return User(0.0, 0.0)
    }

    fun locationUpdate(latitude : Double, longitude: Double) {
        var user = user.value
        user?.latitude = latitude
        user?.longitude = longitude

        _users.value = user

        val call = repository.updateUserLocation(latitude, longitude)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "update success")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, "callback fail")
                Log.d(TAG, "locationUpdate " + t.message)
            }

        })
    }

    fun executeFcmServiceToken(token: String) {
        val call = repository.executeFcmServiceToken(token)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "update success")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, "callback fail")
                Log.d(TAG, "" + t.message)
            }

        })
    }
}