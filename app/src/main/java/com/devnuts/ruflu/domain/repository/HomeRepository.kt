package com.devnuts.ruflu.domain.repository

import com.devnuts.ruflu.comm.retrofit.ServerAPI
import com.devnuts.ruflu.comm.retrofit.RufluApp
import com.devnuts.ruflu.home.model.UserDtl
import com.devnuts.ruflu.home.model.UserCard
import retrofit2.*

class HomeRepository  {

    private val api = RufluApp.retrofit.create(ServerAPI::class.java)

    fun getUserCardList() : Call<List<UserCard>> {

        return api.getUsercardList()

    }

    fun getNBUserList() : Call<List<UserDtl>> {

        return api.getNBUserList()
    }

    fun insertHateUserCard(userCard : HashMap<String, String>) : Call<String> {

        return api.insertHateUserCard(userCard)
    }

    fun insertLikeUserCard(userCard : HashMap<String, String>) : Call<String> {

        return api.insertLikeUserCard(userCard)
    }

    fun getNBUserDtl(userId: String): Call<UserDtl> {

        return api.getNBUserDtl(userId)
    }

    fun changeRatingToUser(rating: Float, userId : String) : Call<String> {
        return api.changeRatingToUser(rating, userId)
    }
}