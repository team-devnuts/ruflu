package com.devnuts.ruflu.data.repository

import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.data.source.SomeDataSource
import com.devnuts.ruflu.domain.repository.SomeRepository
import retrofit2.Call
import javax.inject.Inject

class SomeRepositoryImpl @Inject constructor(
    private val someDataSource: SomeDataSource
) : SomeRepository {
    override fun getLikeMeList(): Call<ArrayList<UserModel>> {
        return someDataSource.getLikeMeList()
    }

    override fun addUserInMyMatchList(userId: String): Call<String> {
        return someDataSource.addUserInMyMatchList(userId)
    }
}