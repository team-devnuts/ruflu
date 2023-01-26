package com.devnuts.ruflu.util

import com.devnuts.ruflu.data.api.response.card.UserModel
import com.devnuts.ruflu.ui.model.chat.ChatRoom
import retrofit2.Call
import retrofit2.http.*

interface ServerAPI {

    /** Home **/
    @GET("home/users") // 변경 예정 userList
    fun getUserList(): Call<List<UserModel>>

    @FormUrlEncoded
    @POST("home/hate")
    fun addUserInMyHateList(@FieldMap param: HashMap<String, String>): Call<String>

    @FormUrlEncoded
    @POST("home/like")
    fun addUserInMyLikeList(@FieldMap param: HashMap<String, String>): Call<String>

    /** Some **/
    @GET("some/like/me")
    fun getLikeMeList(): Call<ArrayList<UserModel>>

    @FormUrlEncoded
    @POST("some/match")
    fun addUserInMyMatchList(@Field("otherUserId") userId: String): Call<String>

    /** Match **/
    @GET("some/match")
    fun getUserMatchedWithMeList(): Call<ArrayList<UserModel>>

    // 미완성 보류 (리턴값 response 객체로 수정)
    @PATCH("some/match")
    fun deleteUserMatchedWithMe(@Field("otherUserId") userId: String): Call<List<String>>

    /** Main **/
    @FormUrlEncoded
    @POST("main/location")
    fun updateUserLocation(
        @Field("latitude") latitude: Double,
        @Field("longitude") longitude: Double
    ): Call<String>

    @FormUrlEncoded
    @POST("main/alarm")
    fun updateAlarmToken(
        @Field("token") token: String
    ): Call<String>
}

//    // 채팅 api
//    @GET("chat/list")
//    fun getMyChattingRoomList(): Call<List<ChatRoom>>

//    @GET("home/seLv1/userDtl/{userId}")
//    fun getUserDetail(@Path("userId") userId: String): Call<SomeUser>

//    @FormUrlEncoded
//    @POST("home/remove/like")
//    fun deleteSomeUser(@Field("toUser") userId: String?): Call<String>