package com.devnuts.ruflu.data.api.response.home

import com.devnuts.ruflu.data.api.response.home.model.UserModel

data class UserResponse(
    val code: String,
    val message: String,
    val result: UserModel
)