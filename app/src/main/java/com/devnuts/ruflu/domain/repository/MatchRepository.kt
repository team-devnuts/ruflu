package com.devnuts.ruflu.domain.repository

import com.devnuts.ruflu.data.api.response.home.model.UserModel
import retrofit2.Call

interface MatchRepository {

    fun getUserMatchedWithMeList(): Call<ArrayList<UserModel>>
}