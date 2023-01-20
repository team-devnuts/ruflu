package com.devnuts.ruflu.domain.repository

import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.util.RufluApp
import com.devnuts.ruflu.util.ServerAPI
import retrofit2.*

class HomeRepository {

    private val api = RufluApp.retrofit.create(ServerAPI::class.java)

    fun getUserCardList(): Call<List<UserModel>> {
        return api.getUserCardList()
    }

    fun insertHateUserCard(userCard: HashMap<String, String>): Call<String> {
        return api.insertHateUserCard(userCard)
    }

    fun insertLikeUserCard(userCard: HashMap<String, String>): Call<String> {
        return api.insertLikeUserCard(userCard)
    }

    fun changeRatingToUser(rating: Float, userId: String): Call<String> {
        return api.changeRatingToUser(rating, userId)
    }
}
