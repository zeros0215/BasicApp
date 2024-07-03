//package com.study.basicapp.utils
//
//import android.content.SharedPreferences
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Singleton
//class PreferencesHelper @Inject constructor(
//    private val sharedPreferences: SharedPreferences
//) {
//
//    companion object{
//        private val PRE_KEY_USERNAME = "preferences.key_username"
//    }
//
//    fun setUsername(name : String = ""){
//        sharedPreferences.edit().putString(PRE_KEY_USERNAME, name).apply()
//    }
//
//    fun getUsername(): String? {
//        return sharedPreferences.getString(PRE_KEY_USERNAME, "")
//    }
//
//
//
//    /*
//        common case
//    */
//    fun setString(key: String, value: String) {
//        sharedPreferences.edit().putString(key, value).apply()
//    }
//
//    fun getString(key: String, defaultValue: String = ""): String? {
//        return sharedPreferences.getString(key, defaultValue)
//    }
//
//    fun setInt(key: String, value: Int) {
//        sharedPreferences.edit().putInt(key, value).apply()
//    }
//
//    fun getInt(key: String, defaultValue: Int = 0): Int {
//        return sharedPreferences.getInt(key, defaultValue)
//    }
//
//    fun setBoolean(key: String, value: Boolean) {
//        sharedPreferences.edit().putBoolean(key, value).apply()
//    }
//
//    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
//        return sharedPreferences.getBoolean(key, defaultValue)
//    }
//
//    // 필요에 따라 다른 데이터 타입에 대한 메서드도 추가할 수 있습니다.
//}
