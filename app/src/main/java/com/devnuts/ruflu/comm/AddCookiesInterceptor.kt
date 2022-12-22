package com.devnuts.ruflu.comm

import com.devnuts.ruflu.comm.retrofit.RufluApp
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 로그인시 사용자 정보를 가져오기 위한 쿠키
 */
class AddCookiesInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()

        // Preference 에서 cookies 를 가져오는 작업을 수행
        /*
        var preference = RufluApp.sharedPreference.getSettingCookie("cookie")

        preference?.forEach {
            builder.addHeader("Cookie", it)
        }
        */
        val preference = RufluApp.sharedPreference.getSettingString("user_id")

        if (preference != null)
            builder.addHeader("user_id", preference)

        builder.removeHeader("User-Agent").addHeader("User-Agent", "Android")
        return chain.proceed(builder.build())
    }
}
