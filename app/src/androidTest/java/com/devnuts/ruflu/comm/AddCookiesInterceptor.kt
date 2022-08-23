package com.devnuts.ruflu.comm

import com.devnuts.ruflu.comm.retrofit.RufluApp
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
// 로그인시 사용자 정보를 가져오기 위한 쿠기
class AddCookiesInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var builder = chain.request().newBuilder()

        // Preference 에서 cookies를 가져오는 작업을 수행
        /*
        var preference = RufluApp.sharedPreference.getSettingCookie("cookie")

        preference?.forEach {
            builder.addHeader("Cookie", it)
        }
        */
        var preference = RufluApp.sharedPreference.getSettingString("user_id")

        if(preference != null)
            builder.addHeader("user_id", preference)

        builder.removeHeader("User-Agent").addHeader("User-Agent", "Android")
        return chain.proceed(builder.build())
    }
}