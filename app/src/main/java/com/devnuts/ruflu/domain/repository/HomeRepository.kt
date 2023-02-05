package com.devnuts.ruflu.domain.repository

import com.devnuts.ruflu.data.api.response.NetworkResponse
import com.devnuts.ruflu.data.api.response.home.UserListResponse
import com.devnuts.ruflu.data.api.response.home.UserResponse
import com.devnuts.ruflu.domain.entities.UserEntity
import retrofit2.*

interface HomeRepository {
    suspend fun getUserList(): Result<List<UserEntity>>

    suspend fun addUserInMyHateList(userCard: HashMap<String, String>): Result<NetworkResponse>

    suspend fun addUserInMyLikeList(userCard: HashMap<String, String>): Result<NetworkResponse>
}