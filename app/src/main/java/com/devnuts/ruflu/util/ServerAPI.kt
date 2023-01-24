package com.devnuts.ruflu.util

import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.ui.model.chat.ChatRoom
import com.devnuts.ruflu.ui.model.home.UserUIModel
import com.devnuts.ruflu.ui.model.like.SomeUser
import retrofit2.Call
import retrofit2.http.*

interface ServerAPI {

    @GET("home/userCardList")
    fun getUserCardList(): Call<List<UserModel>>

    @FormUrlEncoded
    @POST("home/ins/hate")
    fun insertHateUserCard(@FieldMap param: HashMap<String, String>): Call<String>

    @FormUrlEncoded
    @POST("home/ins/like")
    fun insertLikeUserCard(@FieldMap param: HashMap<String, String>): Call<String>

    @FormUrlEncoded
    @POST("home/chg/star")
    fun changeRatingToUser(
        @Field("star") rating: Float,
        @Field("userId") userId: String
    ): Call<String>

    @FormUrlEncoded
    @POST("main/loca/udt")
    fun updateUserLocation(
        @Field("latitude") latitude: Double,
        @Field("longitude") longitude: Double
    ): Call<String>

    @FormUrlEncoded
    @POST("main/token/udt")
    fun executeFcmServiceToken(@Field("token") token: String): Call<String>

    @GET("home/seLv1List")
    fun getSeLv1User(): Call<ArrayList<UserModel>>

    @GET("home/seLv1/userDtl/{userId}")
    fun getSeLv1UserDtl(@Path("userId") userId: String): Call<SomeUser>

    @FormUrlEncoded
    @POST("home/seLv1/like")
    fun insertSeLikeLv2(@Field("userId") userId: String): Call<String>

    @GET("home/seLv2List")
    fun getSeLv2User(): Call<ArrayList<UserModel>>

    @FormUrlEncoded
    @POST("home/remove/like")
    fun deleteLikeUser(@Field("toUser") userId: String?): Call<String>

    // 채팅 api
    @GET("chat/list")
    fun getMyChattingRoomList(): Call<List<ChatRoom>>
}
