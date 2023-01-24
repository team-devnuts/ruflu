package com.devnuts.ruflu.domain.repository

import com.devnuts.ruflu.data.api.response.card.UserModel
import retrofit2.*

interface HomeRepository {

    //private val api = RufluApp.retrofit.create(ServerAPI::class.java)

    fun getUserList(): Call<List<UserModel>>

    fun addUserInMyHateList(userCard: HashMap<String, String>): Call<String>

    fun addUserInMyLikeList(userCard: HashMap<String, String>): Call<String>
}