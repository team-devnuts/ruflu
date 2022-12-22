package com.devnuts.ruflu.worker

import java.text.SimpleDateFormat
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class LogInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        Timber.tag(TAG).d("${getNow()} request:method=${request.method} url=${request.url}")
        Timber.tag(TAG).d("${getNow()} request:header ${request.headers}")
        Timber.tag(TAG).d("${getNow()} request:is https ? ${request.isHttps}")
        Timber.tag(TAG).d("${getNow()} response:code=${response.code} message=${response.message}")

        return response
    }

    private fun getNow(): String {
        return SimpleDateFormat(PATTERN).format(System.currentTimeMillis())
    }

    companion object {
        private const val TAG = "API"
        private const val PATTERN = "yyyy-mm-dd HH:mm:ss"
    }
}
