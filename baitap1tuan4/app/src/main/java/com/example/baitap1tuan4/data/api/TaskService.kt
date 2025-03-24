package com.example.baitap1tuan4.data.api

import com.example.baitap1tuan4.data.model.TaskResponse
import retrofit2.http.GET

interface TaskApiService {
    @GET("api/researchUTH/tasks")
    suspend fun getTasks(): TaskResponse
}
