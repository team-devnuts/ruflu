package com.devnuts.ruflu.login.smsAPI.model

data class SMSUser (
    val phoneNumber : String,
    val verificationCode : String,
)
