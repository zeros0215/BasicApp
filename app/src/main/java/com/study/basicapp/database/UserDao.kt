package com.study.basicapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM user_table WHERE name = :selectName")
    suspend fun deleteUserByName(selectName: String)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM user_table WHERE name = :selectName")
    fun selectUserName(selectName: String): Boolean
}