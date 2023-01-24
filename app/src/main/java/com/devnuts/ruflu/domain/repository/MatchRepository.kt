package com.devnuts.ruflu.domain.repository

import com.devnuts.ruflu.data.api.response.card.UserModel
import retrofit2.Call

interface MatchRepository {

    fun getUserMatchedWithMeList(): Call<ArrayList<UserModel>>
}