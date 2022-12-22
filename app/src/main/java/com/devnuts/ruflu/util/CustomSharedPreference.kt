package com.devnuts.ruflu.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Log

class CustomSharedPreference(context: Context) {
    private val ID = "USER_INFO"
    private val mContext = context

    fun putSettingString(key: String, value: String) {
        Log.d("SHARED", "Put $key ( value : $value) to $ID ")
        val preferences: SharedPreferences = mContext.getSharedPreferences(ID, MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    // user Token
    fun getSettingString(key: String): String? {
        Log.d("SHARED", "Get $key from $ID")
        return mContext.getSharedPreferences(ID, 0).getString(key, null)
    }

    fun putSettingInt(key: String, value: Int) {
        Log.d("SHARED", "Put $key ( value : $value) to $ID ")
        val preferences = mContext.getSharedPreferences(ID, MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getSettingInt(key: String): Int? {
        Log.d("SHARED", "Get $key from $ID")
        return mContext.getSharedPreferences(ID, 0).getInt(key, 0)
    }

    fun putSettingCookie(key: String, value: HashSet<String>) {
        Log.d("SHARED", "Put $key ( value : $value) to $ID ")
        val preferences = mContext.getSharedPreferences(ID, MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putStringSet(key, value)
        editor.apply()
    }

    fun getSettingCookie(key: String): MutableSet<String>? {
        Log.d("SHARED", "Get $key from $ID")
        return mContext.getSharedPreferences(ID, 0).getStringSet(key, null)
    }
}
