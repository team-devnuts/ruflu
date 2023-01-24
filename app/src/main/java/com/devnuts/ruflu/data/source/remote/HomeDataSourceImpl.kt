package com.devnuts.ruflu.data.source.remote

import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.data.source.HomeDataSource
import com.devnuts.ruflu.util.ServerAPI
import retrofit2.Call
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val serverAPI: ServerAPI
) : HomeDataSource {

    override fun getUserList(): Call<List<UserModel>> {
        return serverAPI.getUserList()
    }

    override fun addUserInMyHateList(userCard: HashMap<String, String>): Call<String> {
        return serverAPI.addUserInMyHateList(userCard)
    }

    override fun addUserInMyLikeList(userCard: HashMap<String, String>): Call<String> {
        return serverAPI.addUserInMyLikeList(userCard)
    }
}