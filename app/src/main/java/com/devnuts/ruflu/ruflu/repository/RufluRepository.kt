package com.devnuts.ruflu.ruflu.repository

import com.devnuts.ruflu.comm.retrofit.RufluApp
import com.devnuts.ruflu.comm.retrofit.ServerAPI
import com.devnuts.ruflu.ui.model.home.UserDtl
import com.devnuts.ruflu.ruflu.fragment.model.LikeLv1User
import retrofit2.Call

class RufluRepository {
    private val api = RufluApp.retrofit.create(ServerAPI::class.java)

    fun getSeLv1User() : Call<List<UserDtl>> {
        return api.getSeLv1User()
    }

    fun getSeLv1UserDtl(userId: String) : Call<LikeLv1User> {
        return api.getSeLv1UserDtl(userId)
    }

    fun insertSeLikeLv2(userId: String) : Call<String> {
        return api.insertSeLikeLv2(userId)
    }

    fun getSeLv2User() : Call<List<UserDtl>> {
        return api.getSeLv2User()
    }

    fun deleteLikeUser(userId: String?): Call<String> {
        return api.deleteLikeUser(userId)
    }

}