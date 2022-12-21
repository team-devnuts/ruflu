package com.devnuts.ruflu.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log

object SharedPreferenceToken {

    private val TOKEN = "TOKEN"

    fun putSettingItem(mContext: Context, key: String, value: String) {
        // SharedPreferences 를 선언과 동시에 초기화를 시킨다.
        val preference: SharedPreferences = mContext.getSharedPreferences(TOKEN, MODE_PRIVATE)
        val editor = preference.edit()

        // 원하는 값을 불러와서, 저장
        editor.putString(key, value)
        editor.apply()
    }

    fun getSettingItem(mContext: Context, key: String): String? {
        Log.d("SHARED", "Get $key from $TOKEN")
        return mContext.getSharedPreferences(TOKEN, 0).getString(key, null)
    }
}
