package com.devnuts.ruflu.data.source.remote

import android.util.Log
import com.devnuts.ruflu.data.api.response.NetworkResponse
import com.devnuts.ruflu.data.api.response.home.model.toEntity
import com.devnuts.ruflu.data.source.HomeDataSource
import com.devnuts.ruflu.domain.entities.UserDetailEntity
import com.devnuts.ruflu.domain.entities.UserEntity
import com.devnuts.ruflu.util.RufluApiService
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val api: RufluApiService
) : HomeDataSource {

    override suspend fun getUserList(): Result<List<UserEntity>> = try {
        val response = api.getUserList()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Log.d("flow", "${response.body()}")
                Result.success(body.result.map { it.toEntity() })
            } else {
                Result.success(emptyList())
            }
        } else {
            throw Exception()
        }
    } catch (exception: Exception) {
        Result.failure<Nothing>(exception)
    }

    override suspend fun addUserInMyHateList(
        userCard: HashMap<String, String>
    ): Result<NetworkResponse> = try {
        val response = api.addUserInMyHateList(userCard)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Result.success(NetworkResponse("200", "path"))
            } else {
                Result.success(NetworkResponse("111", "null"))
            }
        } else {
            throw Exception()
        }
    } catch (exception: Exception) {
        Result.failure<Nothing>(exception)
    }

    override suspend fun addUserInMyLikeList(
        userCard: HashMap<String, String>
    ): Result<NetworkResponse> = try {
        val response = api.addUserInMyLikeList(userCard)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Result.success(NetworkResponse("200", "path"))
            } else {
                Result.success(NetworkResponse("111", "null"))
            }
        } else {
            throw Exception()
        }
    } catch (exception: Exception) {
        Result.failure<Nothing>(exception)
    }

    override suspend fun getUserDetailInfo(
        userId: String
    ): Result<UserDetailEntity> = try {
        val response = api.getUserDetail(userId)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Result.success((body.result).toEntity())

            } else {
                Log.d("flow", "무조건 뻑난다.")
                Result.success(response.body()?.result!!.toEntity())
            }
        } else {
            Log.d("flow", "response 실패.")
            throw Exception()
        }
    } catch (exception: Exception) {
        Log.d("flow", "${exception.message}")
        Log.d("flow", "${exception.toString()}")
        Log.d("flow", "${exception.cause}")
        Result.failure<Nothing>(exception)
    }
}