package com.devnuts.ruflu.comm.retrofit

import com.devnuts.ruflu.chat.fragment.model.ChatRoom
import com.devnuts.ruflu.home.model.UserDtl
import com.devnuts.ruflu.home.model.UserCard
import com.devnuts.ruflu.ruflu.fragment.model.LikeLv1User
import com.devnuts.ruflu.ruflu.fragment.model.LikeLv2User
import retrofit2.Call
import retrofit2.http.*


interface ServerAPI {

    @GET("/home/userCardList")
    fun getUsercardList() : Call<List<UserCard>>

    @FormUrlEncoded
    @POST("/home/ins/hate")
    fun insertHateUserCard(@FieldMap param : HashMap<String, String>) : Call<String>

    @FormUrlEncoded
    @POST("/home/ins/like")
    fun insertLikeUserCard(@FieldMap param : HashMap<String, String>) : Call<String>

    @GET("/home/NbUserDtl/{position}")
    fun getNBUserDtl(@Path("userId") userId: String): Call<UserDtl>

    @FormUrlEncoded
    @POST("/home/chg/star")
    fun changeRatingToUser(@Field("star") rating: Float, @Field("userId") userId: String): Call<String>



    @GET("/home/NbUserList")
    fun getNBUserList() : Call<List<UserDtl>>

    @FormUrlEncoded
    @POST("/main/loca/udt")
    fun updateUserLocation(@Field("latitude") latitude : Double, @Field("longitude") longitude : Double) : Call<String>

    @FormUrlEncoded
    @POST("/main/token/udt")
    fun executeFcmServiceToken(@Field("token") token: String) : Call<String>



    @GET("/home/seLv1List")
    fun getSeLv1User() : Call<List<UserDtl>>

    @GET("/home/seLv1/userDtl/{userId}")
    fun getSeLv1UserDtl(@Path("userId") userId: String) : Call<LikeLv1User>

    @FormUrlEncoded
    @POST("/home/seLv1/like")
    fun insertSeLikeLv2(@Field("userId") userId: String): Call<String>

    @GET("/home/seLv2List")
    fun getSeLv2User(): Call<List<UserDtl>>

    @FormUrlEncoded
    @POST("/home/remove/like")
    fun deleteLikeUser(@Field("toUser") userId: String?): Call<String>

    //채팅 api
    @GET("/chat/list")
    fun getMyChattingRoomList(): Call<List<ChatRoom>>

}





