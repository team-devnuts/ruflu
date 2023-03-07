package com.devnuts.ruflu.data.api.response.signin

import com.devnuts.ruflu.data.api.response.RufluResponse

data class ResponseAuthCode(
    val code: String,
    val message: String,
    val result: SMSData
)

