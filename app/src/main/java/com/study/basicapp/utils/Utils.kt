package com.study.basicapp.utils

import android.annotation.SuppressLint
import android.util.Log
import com.study.basicapp.repository.PreferencesRepository
import com.study.basicapp.repository.RemoteRespository
import com.study.basicapp.repository.UserItemRespository
import com.study.basicapp.di.module.DiUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object Utils {

    private val TAG = "Utils"

    fun getCurrentTime(): String {
        val now = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss")
        return now.format(formatter)
    }


    @SuppressLint("SuspiciousIndentation")
    fun testDi(){

        val scope = CoroutineScope(Dispatchers.Default)
            scope.launch {
                val userItemRepositry : UserItemRespository = DiUtil.userItemRepository
                Log.d(TAG, "items: " + userItemRepositry.getAllUsers().toString())
            }

        val scope2 = CoroutineScope(Dispatchers.Default)
        scope2.launch {
            val remoteRepositry : RemoteRespository = DiUtil.remoteRepository
            Log.d(TAG, "remote items: " + remoteRepositry.getUsers().toString())
        }

        val preferenceRepositry : PreferencesRepository = DiUtil.preferenceRepository
        preferenceRepositry.setUsername("CHOI choi choi")
        Log.d(TAG, "preferenceRepositry name: " + preferenceRepositry.getUsername())

    }


}