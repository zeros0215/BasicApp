package com.study.basicapp.repository

import com.study.data.datasource.remote.api.ApiService
import com.study.basicapp.remote.UsersDto
import retrofit2.Call
import javax.inject.Inject

class RemoteRespository @Inject constructor(private val apiService: ApiService) {

    suspend fun getUsers() : Call<List<UsersDto>> {
        return apiService.getUsers()
    }

    suspend fun getUsersId(userId : Int) : Call<UsersDto> {
        return apiService.getUserId(userId)
    }

}