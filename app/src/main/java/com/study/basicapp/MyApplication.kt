package com.study.basicapp

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.study.basicapp.local.AppDatabase
import dagger.hilt.android.HiltAndroidApp

/*
@HiltAndoridApp 어노테이션을 달아줌으로써, 컴파일 타임 시 표준 컴포넌트 빌딩에 필요한 클래스들을 초기화를 해줍니다.
따라서 Hilt를 사용하는 모든 앱은 @HiltAndroidApp 이 달린 Application 클래스를 반드시 포함해야 합니다.
라고하는데, 그냥 쉽게쉽게 이해하자. " 이 어플에서 Hilt 쓸게요...! 라고 하는 것 " 이다.
[출처] Hilt를 사용하여 안드로이드에서 D.I 쉽게 사용하기! -1 (Kotlin)|작성자 tgyuu

androidmanifest => android:name=".MyApplication" 추가
*/
@HiltAndroidApp
class MyApplication : Application(){
    private val TAG = "MyApplication"

    init {
        instance = this
    }

    companion object{
        lateinit var instance  : MyApplication
        var database: AppDatabase? = null

        fun applicationContext() : Context{
            return instance.applicationContext
        }

    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "Application onCreate")

        //For Room
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_rooms.db"
        ).build()

    }

}