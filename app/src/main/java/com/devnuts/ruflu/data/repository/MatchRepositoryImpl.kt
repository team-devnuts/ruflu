package com.devnuts.ruflu.data.repository

import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.data.source.MatchDataSource
import com.devnuts.ruflu.domain.repository.MatchRepository
import retrofit2.Call
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val matchDataSource: MatchDataSource
) : MatchRepository {

    override fun getUserMatchedWithMeList(): Call<ArrayList<UserModel>> {
        return matchDataSource.getUserMatchedWithMeList()
    }
}