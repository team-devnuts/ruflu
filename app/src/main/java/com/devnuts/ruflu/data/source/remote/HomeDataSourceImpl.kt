package com.devnuts.ruflu.data.source.remote

import android.util.Log
import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.data.source.HomeDataSource
import com.devnuts.ruflu.util.ServerAPI
import retrofit2.Call
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val serverAPI: ServerAPI
) : HomeDataSource {

    override fun getUsers(): Call<List<UserModel>> {
        return serverAPI.getUserCardList()
    }

    override fun addHateUser(userCard: HashMap<String, String>): Call<String> {
        return serverAPI.insertHateUserCard(userCard)
    }

    override fun addLikeUser(userCard: HashMap<String, String>): Call<String> {
        return serverAPI.insertLikeUserCard(userCard)
    }
}