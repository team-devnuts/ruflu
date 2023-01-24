package com.devnuts.ruflu.domain.repository

import com.devnuts.ruflu.data.api.response.card.UserModel
import retrofit2.Call

interface SomeRepository {
    fun getLikeMeList(): Call<ArrayList<UserModel>>

    fun addUserInMyMatchList(userId: String): Call<String>
}
