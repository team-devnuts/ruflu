package com.devnuts.ruflu.domain.repository

import com.devnuts.ruflu.data.api.response.card.UserModel
import retrofit2.*

interface HomeRepository {

    //private val api = RufluApp.retrofit.create(ServerAPI::class.java)

    fun getUsers(): Call<List<UserModel>>

    fun addHateUser(userCard: HashMap<String, String>): Call<String>

    fun addLikeUser(userCard: HashMap<String, String>): Call<String>
}