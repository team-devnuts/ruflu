package com.devnuts.ruflu.data.repository

import android.util.Log
import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.data.source.HomeDataSource
import com.devnuts.ruflu.domain.repository.HomeRepository
import retrofit2.Call
import javax.inject.Inject


class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {

    override fun getUsers(): Call<List<UserModel>> {
        return homeDataSource.getUsers()
    }

    override fun addHateUser(userCard: HashMap<String, String>): Call<String> {
        return homeDataSource.addHateUser(userCard)
    }

    override fun addLikeUser(userCard: HashMap<String, String>): Call<String> {
        return homeDataSource.addLikeUser(userCard)
    }
}