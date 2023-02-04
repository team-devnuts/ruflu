package com.devnuts.ruflu.domain.repository

import retrofit2.Call

interface MainRepository {

    fun updateUserLocation(latitude: Double, longitude: Double): Call<String>

    fun executeFcmServiceToken(token: String): Call<String>
}