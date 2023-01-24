package com.devnuts.ruflu.data.source

import com.devnuts.ruflu.data.api.response.card.UserModel
import retrofit2.Call

interface HomeDataSource {

    fun getUserList(): Call<List<UserModel>>

    fun addUserInMyHateList(userCard: HashMap<String, String>): Call<String>

    fun addUserInMyLikeList(userCard: HashMap<String, String>): Call<String>
}