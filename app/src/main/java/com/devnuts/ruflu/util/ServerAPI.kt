package com.devnuts.ruflu.util

import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.ui.model.chat.ChatRoom
import retrofit2.Call
import retrofit2.http.*

interface ServerAPI {

    @GET("home/userCardList")
    fun getUserList(): Call<List<UserModel>>

    @FormUrlEncoded
    @POST("home/ins/hate")
    fun addUserInMyHateList(@FieldMap param: HashMap<String, String>): Call<String>

    @FormUrlEncoded
    @POST("home/ins/like")
    fun addUserInMyLikeList(@FieldMap param: HashMap<String, String>): Call<String>

    @GET("home/seLv1List")
    fun getLikeMeList(): Call<ArrayList<UserModel>>

    @FormUrlEncoded
    @POST("home/seLv1/like")
    fun addUserInMyMatchList(@Field("userId") userId: String): Call<String>

    @FormUrlEncoded
    @POST("main/loca/udt")
    fun updateUserLocation(
        @Field("latitude") latitude: Double,
        @Field("longitude") longitude: Double
    ): Call<String>

    @FormUrlEncoded
    @POST("main/token/udt")
    fun executeFcmServiceToken(@Field("token") token: String): Call<String>


    @GET("home/seLv2List")
    fun getUserMatchedWithMeList(): Call<ArrayList<UserModel>>

    // 채팅 api
    @GET("chat/list")
    fun getMyChattingRoomList(): Call<List<ChatRoom>>
}



//    @GET("home/seLv1/userDtl/{userId}")
//    fun getUserDetail(@Path("userId") userId: String): Call<SomeUser>

//    @FormUrlEncoded
//    @POST("home/remove/like")
//    fun deleteSomeUser(@Field("toUser") userId: String?): Call<String>