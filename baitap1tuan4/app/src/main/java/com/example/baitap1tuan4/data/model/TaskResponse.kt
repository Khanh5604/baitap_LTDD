package com.example.baitap1tuan4.data.model

import com.google.gson.annotations.SerializedName

// Đây là lớp bao bọc dữ liệu API trả về
data class TaskResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Task>?
)
