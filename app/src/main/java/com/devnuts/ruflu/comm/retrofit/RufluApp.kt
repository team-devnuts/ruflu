package com.devnuts.ruflu.comm.retrofit

import android.app.Application
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

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
        appNotification = AppNotification(this)
        sharedPreference = CustomSharedPreference(this)
        initRetrofit()
    }

    private fun initRetrofit() {
        val okHttpClient = OkHttpClient().newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(AddCookiesInterceptor())
            .addInterceptor(ReceivedCookiesInterceptor())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("$url:$port")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    companion object {
        const val url = "http://192.168.0.6"
        const val port = 8005
        lateinit var retrofit: Retrofit
        lateinit var appNotification: AppNotification
        lateinit var sharedPreference: CustomSharedPreference
    }
}
