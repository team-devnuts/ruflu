package com.devnuts.ruflu.data.api

import com.devnuts.ruflu.data.api.request.signin.RequestLoginData
import com.devnuts.ruflu.data.api.response.signin.ResponseLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/login/oauthAPI")
    fun postLogin(
        @Body body: RequestLoginData
    ): Call<ResponseLoginData>
}
