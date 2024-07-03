package com.study.basicapp.database

import androidx.room.Room
import com.study.basicapp.MyApplication

object DatabaseManager {

    var database: AppDatabase? = null

    fun init(myApplication: MyApplication) {
        //For Room
        database = Room.databaseBuilder(
            myApplication,
            AppDatabase::class.java, "app_rooms.db"
        ).build()
    }
}