package com.study.basicapp.utils

import android.content.Context
import com.study.basicapp.preferences.PreferencesManager
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

object Utils {

    fun getCurrentTime(): String {
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss")
        return now.format(formatter)
    }

    fun getPrefUserName() : String? {
        return PreferencesManager.getUsername()
    }

}