package com.example.signwithgg.service

import com.example.signwithgg.model.ApiResponse
import com.example.signwithgg.model.ApiResponseSingle
import com.example.signwithgg.model.Task
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    // Lấy tất cả task
    @GET("researchUTH/tasks")
    suspend fun getTasks(): ApiResponse

    // Lấy chi tiết 1 task
    @GET("researchUTH/task/{id}")
    suspend fun getTaskDetail(@Path("id") id: Int): ApiResponseSingle

    // Xóa 1 task
    @DELETE("researchUTH/task/{id}")
    suspend fun deleteTask(@Path("id") id: Int)
}

object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://amock.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
