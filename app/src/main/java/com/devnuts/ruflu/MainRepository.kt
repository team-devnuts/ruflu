package com.devnuts.ruflu

import com.devnuts.ruflu.util.RufluApp
import com.devnuts.ruflu.util.ServerAPI
import retrofit2.Call

class MainRepository {

    private val api = RufluApp.retrofit.create(ServerAPI::class.java)

    fun updateUserLocation(latitude: Double, longitude: Double): Call<String> {

        return api.updateUserLocation(latitude, longitude)
    }

    fun executeFcmServiceToken(token: String): Call<String> {

        return api.executeFcmServiceToken(token)
    }
}
