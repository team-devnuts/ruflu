package com.devnuts.ruflu.domain.repository

import android.util.Log
import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.ui.model.home.UserUIModel
import com.devnuts.ruflu.ui.model.like.SomeUser
import com.devnuts.ruflu.util.RufluApp
import com.devnuts.ruflu.util.ServerAPI
import retrofit2.Call

class SomeRepository {
    private val api = RufluApp.retrofit.create(ServerAPI::class.java)

    fun getLikeMeList(): Call<ArrayList<UserModel>> {
        return api.getSeLv1User()
    }

    fun getSeLv1UserDtl(userId: String): Call<SomeUser> {
        return api.getSeLv1UserDtl(userId)
    }

    fun insertSeLikeLv2(userId: String): Call<String> {
        return api.insertSeLikeLv2(userId)
    }

    fun getSeLv2User(): Call<ArrayList<UserModel>> {
        return api.getSeLv2User()
    }

    fun deleteLikeUser(userId: String?): Call<String> {
        return api.deleteLikeUser(userId)
    }
}
