package com.devnuts.ruflu.data.source

import com.devnuts.ruflu.data.api.response.NetworkResponse
import com.devnuts.ruflu.domain.entities.UserEntity

interface SomeDataSource {

    suspend fun getLikeMeList(): Result<List<UserEntity>>

    suspend fun addUserInMyMatchList(userId: String): Result<NetworkResponse>

    suspend fun getUserMatchedWithMeList(): Result<List<UserEntity>>
}