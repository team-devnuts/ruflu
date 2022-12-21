package com.devnuts.ruflu.comm

import android.util.Log
import java.text.SimpleDateFormat
import okhttp3.Interceptor
import okhttp3.Response

class LogInterceptor : Interceptor {
    private val TAG = "API"
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        Log.d(TAG, "${getNow()} request:method=${request.method} url=${request.url}")
        Log.d(TAG, "${getNow()} request:header ${request.headers}")
        Log.d(TAG, "${getNow()} request:is https ? ${request.isHttps}")
        Log.d(TAG, "${getNow()} response:code=${response.code} message=${response.message}")

        return response
    }

    private fun getNow(): String {
        return SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(System.currentTimeMillis())
    }
}
