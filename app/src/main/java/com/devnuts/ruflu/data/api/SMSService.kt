package com.devnuts.ruflu.data.api

import com.devnuts.ruflu.data.api.request.signin.RequestSMSData
import com.devnuts.ruflu.data.api.response.signin.ResponseSMSData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SMSService {
    @POST("/login/smsAPI")
    fun smsAPI(
        @Body body: RequestSMSData
    ): Call<ResponseSMSData>
}
