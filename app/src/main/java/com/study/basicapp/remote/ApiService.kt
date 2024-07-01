package com.study.data.datasource.remote.api

//import com.study.domain.model.Image
import com.study.hybridbasic.model.UsersDto
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    fun getUsers(): Call<List<UsersDto>>

}

//interface ApiService {
//    @GET("app/UsersDto/email-check") // 맨 앞 / 빼고, 쿼리스트링 전까지만
//    fun getCheckEmail(
//        @Query("email") email: String
//    ): Call<Response> // api 호출 Response 형태로 받을 예정
//}
