package com.devnuts.ruflu.data.repository

import com.devnuts.ruflu.data.api.response.NetworkResponse
import com.devnuts.ruflu.data.api.response.home.model.UserModel
import com.devnuts.ruflu.data.source.SomeDataSource
import com.devnuts.ruflu.domain.entities.UserEntity
import com.devnuts.ruflu.domain.repository.SomeRepository
import retrofit2.Call
import javax.inject.Inject

class SomeRepositoryImpl @Inject constructor(
    private val someDataSource: SomeDataSource
) : SomeRepository {
    override suspend fun getLikeMeList(): Result<List<UserEntity>> {
        return someDataSource.getLikeMeList()
    }

    override suspend fun addUserInMyMatchList(userId: String): Result<NetworkResponse> {
        return someDataSource.addUserInMyMatchList(userId)
    }

    override suspend fun getUserMatchedWithMeList(): Result<List<UserEntity>> {
        return someDataSource.getUserMatchedWithMeList()
    }
}