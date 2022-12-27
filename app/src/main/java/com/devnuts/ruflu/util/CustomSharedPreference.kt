package com.devnuts.ruflu.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class CustomSharedPreference(context: Context) {
    private val mContext = context

    fun putSettingString(key: String, value: String) {
        val preferences: SharedPreferences = mContext.getSharedPreferences(ID, MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    // user Token
    fun getSettingString(key: String): String? {
        return mContext.getSharedPreferences(ID, 0).getString(key, null)
    }

    fun putSettingInt(key: String, value: Int) {
        val preferences = mContext.getSharedPreferences(ID, MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getSettingInt(key: String): Int? {
        return mContext.getSharedPreferences(ID, 0).getInt(key, 0)
    }

    fun putSettingCookie(key: String, value: HashSet<String>) {
        val preferences = mContext.getSharedPreferences(ID, MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putStringSet(key, value)
        editor.apply()
    }

    fun getSettingCookie(key: String): MutableSet<String>? {
        return mContext.getSharedPreferences(ID, 0).getStringSet(key, null)
    }

    companion object {
        private const val ID = "USER_INFO"
    }
}
