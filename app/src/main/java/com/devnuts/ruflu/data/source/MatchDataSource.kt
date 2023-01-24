package com.devnuts.ruflu.data.source

import com.devnuts.ruflu.data.api.response.card.UserModel
import retrofit2.Call

interface MatchDataSource {

    fun getUserMatchedWithMeList(): Call<ArrayList<UserModel>>
}