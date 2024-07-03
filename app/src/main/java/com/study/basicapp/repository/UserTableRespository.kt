package com.study.basicapp.repository

import com.study.basicapp.database.UserDao
import com.study.basicapp.database.UserEntity
import javax.inject.Inject

class UserTableRespository @Inject constructor(private val userDao: UserDao){

    suspend fun insertUser(user : UserEntity){
        userDao.insertUser(user)
    }

    suspend fun deleteUser(user : UserEntity){
        userDao.deleteUser(user)
    }

    suspend fun deleteUserByName(selectName: String){
        userDao.deleteUserByName(selectName)
    }

    suspend fun getAllUsers(): List<UserEntity>{
        return userDao.getAllUsers()
    }

    suspend fun selectUserName(selectName: String): Boolean{
        return userDao.selectUserName(selectName)
    }
}

