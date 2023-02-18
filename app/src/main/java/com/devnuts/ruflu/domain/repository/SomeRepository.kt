package com.devnuts.ruflu.domain.repository

import com.devnuts.ruflu.data.api.response.NetworkResponse
import com.devnuts.ruflu.domain.entities.UserEntity

interface SomeRepository {
    suspend fun getLikeMeList(): Result<List<UserEntity>>

    suspend fun addUserInMyMatchList(userId: String): Result<NetworkResponse>

    suspend fun getUserMatchedWithMeList(): Result<List<UserEntity>>
}
