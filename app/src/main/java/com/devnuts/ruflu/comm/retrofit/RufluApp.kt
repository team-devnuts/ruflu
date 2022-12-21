package com.devnuts.ruflu.comm.retrofit

import android.app.Application
import android.util.Log
import com.devnuts.ruflu.R
import com.devnuts.ruflu.comm.AddCookiesInterceptor
import com.devnuts.ruflu.comm.AppNotification
import com.devnuts.ruflu.comm.ReceivedCookiesInterceptor
import com.devnuts.ruflu.util.CustomSharedPreference
import com.kakao.sdk.common.KakaoSdk
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RufluApp : Application() {
    companion object {
        lateinit var retrofit: Retrofit
        lateinit var appNotification: AppNotification
        lateinit var sharedPreference: CustomSharedPreference
        val url = "http://192.168.0.6"
        // 192.168.123.103
        val port = 8005
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("Global", "come in here (global)")
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
        appNotification = AppNotification(this)
        sharedPreference = CustomSharedPreference(this)
        initRetrofit()
    }

    fun initRetrofit() {
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(AddCookiesInterceptor())
            .addInterceptor(ReceivedCookiesInterceptor())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(url + ":" + port)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }
}
