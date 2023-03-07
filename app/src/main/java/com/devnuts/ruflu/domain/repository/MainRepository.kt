package com.devnuts.ruflu.domain.repository

import com.devnuts.ruflu.data.api.response.signin.ResponseAuthCode
import retrofit2.Call
import retrofit2.Response

interface MainRepository {
    fun updateUserLocation(latitude: Double, longitude: Double): Call<String>

    fun executeFcmServiceToken(token: String): Call<String>

    suspend fun sendUserPhoneNumber(phoneNumber: String): Response<ResponseAuthCode>
}