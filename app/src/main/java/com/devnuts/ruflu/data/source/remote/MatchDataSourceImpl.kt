package com.devnuts.ruflu.data.source.remote

import com.devnuts.ruflu.data.api.response.home.model.UserModel
import com.devnuts.ruflu.data.source.MatchDataSource
import com.devnuts.ruflu.util.RufluApiService
import retrofit2.Call
import javax.inject.Inject

class MatchDataSourceImpl @Inject constructor(
    private val serverAPI: RufluApiService
) : MatchDataSource {

    override fun getUserMatchedWithMeList(): Call<ArrayList<UserModel>> {
        return serverAPI.getUserMatchedWithMeList()
    }
}