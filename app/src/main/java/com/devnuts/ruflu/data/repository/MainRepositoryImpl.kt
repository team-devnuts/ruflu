package com.devnuts.ruflu.data.repository

import com.devnuts.ruflu.data.api.response.signin.ResponseAuthCode
import com.devnuts.ruflu.domain.repository.MainRepository
import com.devnuts.ruflu.util.RufluApiService
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val api: RufluApiService
) : MainRepository {

    override fun updateUserLocation(latitude: Double, longitude: Double): Call<String> {
        return api.updateUserLocation(latitude, longitude)
    }

    override fun executeFcmServiceToken(token: String): Call<String> {
        return api.updateAlarmToken(token)
    }

    override suspend fun sendUserPhoneNumber(phoneNumber: String): Response<ResponseAuthCode> {
        return api.sendUserPhoneNumber(phoneNumber)
    }
}
