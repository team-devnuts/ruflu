package com.devnuts.ruflu.util

import com.devnuts.ruflu.data.api.response.NetworkResponse
import com.devnuts.ruflu.data.api.response.home.UserListResponse
import com.devnuts.ruflu.data.api.response.home.model.UserModel
import com.devnuts.ruflu.ui.model.chat.ChatRoom
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RufluApiService {
    /** Home **/
    @GET("home/users") // 변경 예정 userList
    suspend fun getUserList(): Response<UserListResponse>

    @FormUrlEncoded
    @POST("home/hate")
    suspend fun addUserInMyHateList(@FieldMap param: HashMap<String, String>): Response<NetworkResponse>

    @FormUrlEncoded
    @POST("some/like")
    suspend fun addUserInMyLikeList(@FieldMap param: HashMap<String, String>): Response<NetworkResponse>

    /** Some **/
    @GET("some/like/me")
    fun getLikeMeList(): Call<ArrayList<UserModel>>

    @FormUrlEncoded
    @POST("some/match")
    fun addUserInMyMatchList(@Field("otherUserId") userId: String): Call<String>

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
    fun updateAlarmToken(@Field("token") token: String): Call<String>


    // 채팅 api
    @GET("chat/list")
    fun getMyChattingRoomList(): Call<List<ChatRoom>>
}



//    @GET("home/seLv1/userDtl/{userId}")
//    fun getUserDetail(@Path("userId") userId: String): Call<SomeUser>

//    @FormUrlEncoded
//    @POST("home/remove/like")
//    fun deleteSomeUser(@Field("toUser") userId: String?): Call<String>