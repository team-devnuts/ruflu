package com.devnuts.ruflu.data.source

import com.devnuts.ruflu.data.api.response.NetworkResponse
import com.devnuts.ruflu.data.api.response.home.model.UserModel
import com.devnuts.ruflu.domain.entities.UserEntity
import retrofit2.Call

interface SomeDataSource {

    suspend fun getLikeMeList(): Result<List<UserEntity>>

    suspend fun addUserInMyMatchList(userId: String): Result<NetworkResponse>
}