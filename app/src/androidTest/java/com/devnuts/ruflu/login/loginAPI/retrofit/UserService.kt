package com.devnuts.ruflu.login.loginAPI.retrofit

import com.devnuts.ruflu.login.loginAPI.model.RequestLoginData
import com.devnuts.ruflu.login.loginAPI.model.ResponseLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/login/oauthAPI")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>
}