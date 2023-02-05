package com.devnuts.ruflu.data.api.response.home

import com.devnuts.ruflu.data.api.response.home.model.UserModel

data class UserListResponse(
    val code: String,
    val message: String,
    val result: List<UserModel>
)