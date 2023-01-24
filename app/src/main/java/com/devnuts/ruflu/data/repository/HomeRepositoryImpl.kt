package com.devnuts.ruflu.data.repository

import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.data.source.HomeDataSource
import com.devnuts.ruflu.domain.repository.HomeRepository
import retrofit2.Call
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {

    override fun getUserList(): Call<List<UserModel>> {
        return homeDataSource.getUserList()
    }

    override fun addUserInMyHateList(userCard: HashMap<String, String>): Call<String> {
        return homeDataSource.addUserInMyHateList(userCard)
    }

    override fun addUserInMyLikeList(userCard: HashMap<String, String>): Call<String> {
        return homeDataSource.addUserInMyLikeList(userCard)
    }
}