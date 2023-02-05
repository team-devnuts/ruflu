package com.devnuts.ruflu.data.repository

import com.devnuts.ruflu.data.api.response.NetworkResponse
import com.devnuts.ruflu.data.source.HomeDataSource
import com.devnuts.ruflu.domain.entities.UserEntity
import com.devnuts.ruflu.domain.repository.HomeRepository
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {

    override suspend fun getUserList(): Result<List<UserEntity>> {
        return homeDataSource.getUserList()
    }

    override suspend fun addUserInMyHateList(userCard: HashMap<String, String>): Result<NetworkResponse> {
        return homeDataSource.addUserInMyHateList(userCard)
    }

    override suspend fun addUserInMyLikeList(userCard: HashMap<String, String>): Result<NetworkResponse> {
        return homeDataSource.addUserInMyLikeList(userCard)
    }
}