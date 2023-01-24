package com.devnuts.ruflu.data.source.remote

import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.data.source.SomeDataSource
import com.devnuts.ruflu.util.ServerAPI
import retrofit2.Call
import javax.inject.Inject

class SomeDataSourceImpl @Inject constructor(
    private val serverAPI: ServerAPI
) : SomeDataSource {
    override fun getLikeMeList(): Call<ArrayList<UserModel>> {
        return serverAPI.getLikeMeList()
    }

    override fun addUserInMyMatchList(userId: String): Call<String> {
        return serverAPI.addUserInMyMatchList(userId)
    }
}