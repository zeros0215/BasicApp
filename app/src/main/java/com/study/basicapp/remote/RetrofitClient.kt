package com.study.hybridbasic.remote

import com.study.data.datasource.remote.api.ApiService
import com.study.hybridbasic.common.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_SAMPLE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}