package com.study.data.datasource.remote.api

//import com.study.domain.model.Image
import com.study.hybridbasic.model.users
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getUsers(): Call<List<users>>

}

//interface ApiService {
//    @GET("app/users/email-check") // 맨 앞 / 빼고, 쿼리스트링 전까지만
//    fun getCheckEmail(
//        @Query("email") email: String
//    ): Call<Response> // api 호출 Response 형태로 받을 예정
//}
