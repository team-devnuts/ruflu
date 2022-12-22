package com.devnuts.ruflu.domain.repository

import com.devnuts.ruflu.util.RufluApp
import com.devnuts.ruflu.util.ServerAPI
import com.devnuts.ruflu.ui.model.like.LikeLv1User
import com.devnuts.ruflu.ui.model.home.UserDtl
import retrofit2.Call

class LikeRepository {
    private val api = RufluApp.retrofit.create(ServerAPI::class.java)

    fun getSeLv1User(): Call<List<UserDtl>> {
        return api.getSeLv1User()
    }

    fun getSeLv1UserDtl(userId: String): Call<LikeLv1User> {
        return api.getSeLv1UserDtl(userId)
    }

    fun insertSeLikeLv2(userId: String): Call<String> {
        return api.insertSeLikeLv2(userId)
    }

    fun getSeLv2User(): Call<List<UserDtl>> {
        return api.getSeLv2User()
    }

    fun deleteLikeUser(userId: String?): Call<String> {
        return api.deleteLikeUser(userId)
    }
}
