package com.devnuts.ruflu.data.source

import com.devnuts.ruflu.data.api.response.card.UserModel
import retrofit2.Call

interface HomeDataSource {

    fun getUsers(): Call<List<UserModel>>

    fun addHateUser(userCard: HashMap<String, String>): Call<String>

    fun addLikeUser(userCard: HashMap<String, String>): Call<String>
}