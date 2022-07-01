package com.devnuts.ruflu.login.loginAPI.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object UserClient {
    private const val BASE_URL = "http://10.0.2.2:8005";
    //private const val BASE_URL = "http://127.0.0.1:8005";
    //private const val BASE_URL = "http://172.20.10.8:8005";


    //private const val BASE_URL = "http://210.219.181.149:3000";

    val getApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(UserService::class.java)

}