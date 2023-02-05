package com.devnuts.ruflu.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Context.MODE_WORLD_READABLE
import android.content.SharedPreferences

class CustomSharedPreference(context: Context) {
    private val mContext = context
    private val preferences: SharedPreferences = mContext.getSharedPreferences(ID, MODE_PRIVATE)

    fun putSettingString(key: String, value: String) {
        //val preferences: SharedPreferences = mContext.getSharedPreferences(ID, MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    // user Token
    fun getSettingString(key: String): String {
        return preferences.getString(key, "").toString()
    }

    fun putSettingDouble(key: String, value: Double) {
        val editor = preferences.edit()
        editor.putDouble(key, value)
        editor.apply()
    }

    fun getSettingDouble(key: String): Double {
        return preferences.getDouble(key, 0.0)
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

    // 확장함수
    private fun SharedPreferences.Editor.putDouble(key: String, double: Double) =
        putLong(key, java.lang.Double.doubleToRawLongBits(double))

    private fun SharedPreferences.getDouble(key: String, default: Double) =
        java.lang.Double.longBitsToDouble(getLong(key, java.lang.Double.doubleToRawLongBits(default)))

    companion object {
        private const val ID = "USER_INFO"
    }
}
