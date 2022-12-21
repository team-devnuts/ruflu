package com.devnuts.ruflu.data.api

import com.devnuts.ruflu.data.api.request.RequestLoginData
import com.devnuts.ruflu.data.api.response.ResponseLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/login/oauthAPI")
    fun postLogin(
        @Body body : RequestLoginData
    ) : Call<ResponseLoginData>
}