package com.devnuts.ruflu.login.smsAPI.retrofit

import com.devnuts.ruflu.login.smsAPI.model.RequestSMSData
import com.devnuts.ruflu.login.smsAPI.model.ResponseSMSData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SMSService {
    @POST("/login/smsAPI")
    fun smsAPI(
        @Body body : RequestSMSData
    ) : Call<ResponseSMSData>
}