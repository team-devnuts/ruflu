package com.devnuts.ruflu.data.source.remote

import android.util.Log
import com.devnuts.ruflu.data.api.response.NetworkResponse
import com.devnuts.ruflu.data.api.response.home.model.toEntity
import com.devnuts.ruflu.data.source.SomeDataSource
import com.devnuts.ruflu.domain.entities.UserEntity
import com.devnuts.ruflu.util.RufluApiService
import javax.inject.Inject

class SomeDataSourceImpl @Inject constructor(
    private val api: RufluApiService
) : SomeDataSource {
    override suspend fun getLikeMeList(): Result<List<UserEntity>> = try {
        val response = api.getLikeMeList()
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


    override suspend fun addUserInMyMatchList(userId: String): Result<NetworkResponse> = try {
        val response = api.addUserInMyMatchList(userId)
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

    override suspend fun getUserMatchedWithMeList(): Result<List<UserEntity>> = try {
        val response = api.getUserMatchedWithMeList()
        Log.d("flow", "여기까지도 안온다?")
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
}