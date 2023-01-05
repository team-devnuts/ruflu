package com.devnuts.ruflu.domain.repository

import com.devnuts.ruflu.data.api.response.card.CardModel
import com.devnuts.ruflu.ui.model.home.UserDetailUIModel
import com.devnuts.ruflu.util.RufluApp
import com.devnuts.ruflu.util.ServerAPI
import retrofit2.*

class HomeRepository {

    private val api = RufluApp.retrofit.create(ServerAPI::class.java)

    fun getUserCardList(): Call<List<CardModel>> {
        return api.getUserCardList()
    }

    fun getNBUserList(): Call<List<UserDetailUIModel>> {
        return api.getNBUserList()
    }

    fun insertHateUserCard(userCard: HashMap<String, String>): Call<String> {
        return api.insertHateUserCard(userCard)
    }

    fun insertLikeUserCard(userCard: HashMap<String, String>): Call<String> {
        return api.insertLikeUserCard(userCard)
    }

    fun getNBUserDtl(userId: String): Call<UserDetailUIModel> {
        return api.getNBUserDtl(userId)
    }

    fun changeRatingToUser(rating: Float, userId: String): Call<String> {
        return api.changeRatingToUser(rating, userId)
    }
}
