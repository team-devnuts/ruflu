package com.devnuts.ruflu

import android.app.Application
import com.devnuts.ruflu.util.CustomSharedPreference
import com.devnuts.ruflu.worker.AppNotification
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class RufluApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, getString(R.string.kakao_app_key))
        appNotification = AppNotification(this)
        sharedPreference = CustomSharedPreference(this)
    }

    companion object {
        lateinit var appNotification: AppNotification
        lateinit var sharedPreference: CustomSharedPreference
    }
}
