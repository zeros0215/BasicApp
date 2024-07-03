package com.study.basicapp.preferences

import android.content.Context
import android.content.SharedPreferences

object  PreferencesManager {

    const val BASIC_APP_PREF = "basicapp_prefs"
    private val PRE_KEY_USERNAME = "preferences.key_username"


    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(BASIC_APP_PREF, Context.MODE_PRIVATE)
    }

    fun setUsername(name : String = ""){
        sharedPreferences.edit().putString(PRE_KEY_USERNAME, name).apply()
    }

    fun getUsername(): String? {
        return sharedPreferences.getString(PRE_KEY_USERNAME, "")
    }



    private fun getEditor(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    fun saveString(key: String, value: String) {
        getEditor().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun saveInt(key: String, value: Int) {
        getEditor().putInt(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun saveBoolean(key: String, value: Boolean) {
        getEditor().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    // 필요한 경우 다른 타입의 저장 및 조회 메서드를 추가할 수 있습니다.
}