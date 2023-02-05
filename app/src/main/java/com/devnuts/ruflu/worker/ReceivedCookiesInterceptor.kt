package com.devnuts.ruflu.worker

import com.devnuts.ruflu.RufluApp
import okhttp3.Interceptor
import okhttp3.Response

class ReceivedCookiesInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        /*
        if(!originalResponse.headers("Set-Cookie").isEmpty()) {
            var cookies = HashSet<String>()

            originalResponse.headers("Set-Cookie").forEach {
                cookies.add(it)
            }

            // Preference에 cookies를 넣어주는 작업을 수행
            RufluApp.sharedPreference.putSettingCookie("cookie", cookies)
        }
        */

        if (originalResponse.headers("user_id").isNotEmpty()) {
            val userId = originalResponse.header("user_id")

            RufluApp.sharedPreference.putSettingString("user_id", userId!!)
        }

        return originalResponse
    }
}
