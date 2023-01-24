package com.devnuts.ruflu.data.source

import com.devnuts.ruflu.data.api.response.card.UserModel
import retrofit2.Call

interface SomeDataSource {

    fun getLikeMeList(): Call<ArrayList<UserModel>>

    fun addUserInMyMatchList(userId: String): Call<String>
}