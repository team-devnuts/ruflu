package com.devnuts.ruflu.data.api.response.home

import com.devnuts.ruflu.data.api.response.home.model.UserDetailModel

data class UserResponse(
    val code: String,
    val message: String,
    val result: UserDetailModel
)