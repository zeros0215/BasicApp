package com.study.basicapp.repository

import android.content.SharedPreferences
import com.study.basicapp.remote.UsersDto
import com.study.data.datasource.remote.api.ApiService
import retrofit2.Call
import javax.inject.Inject

class PreferencesRepository @Inject constructor(private val sharedPreferences: SharedPreferences) {

    companion object{
        private val PRE_KEY_USERNAME = "preferences.key_username"
    }

    fun setUsername(name : String = ""){
        sharedPreferences.edit().putString(PRE_KEY_USERNAME, name).apply()
    }

    fun getUsername(): String? {
        return sharedPreferences.getString(PRE_KEY_USERNAME, "")
    }

}
