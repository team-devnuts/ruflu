package com.devnuts.ruflu.util

import android.app.Application
import android.content.Context
import android.util.Log
import com.devnuts.ruflu.R
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    companion object {
        lateinit var instance: GlobalApplication
        fun ApplicationContext(): Context {
            return instance.applicationContext
        }
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("Global", "come in here (global)")
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
    }
}
