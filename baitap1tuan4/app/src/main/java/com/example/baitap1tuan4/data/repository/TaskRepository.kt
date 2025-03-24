package com.example.baitap1tuan4.data.repository

import com.example.baitap1tuan4.data.api.RetrofitInstance
import com.example.baitap1tuan4.data.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository {
    suspend fun getTasks(): List<Task> {
        return withContext(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.getTasks()
                if (response.isSuccess) {
                    response.data ?: emptyList()
                } else {
                    emptyList()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
}
