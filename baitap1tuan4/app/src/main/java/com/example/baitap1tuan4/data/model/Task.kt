package com.example.baitap1tuan4.data.model

import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String = "",
    @SerializedName("status") val status: String = "",
    @SerializedName("priority") val priority: String = "",
    @SerializedName("category") val category: String = "",
    @SerializedName("dueDate") val dueDate: String = "",
    @SerializedName("createdAt") val createdAt: String = "",
    @SerializedName("updatedAt") val updatedAt: String = "",
    @SerializedName("subtasks") val subtasks: List<Subtask> = emptyList(), // ✅ Thêm danh sách subtasks
    @SerializedName("attachments") val attachments: List<Attachment> = emptyList() // ✅ Thêm danh sách attachments
)

// Model cho subtasks
data class Subtask(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("isCompleted") val isCompleted: Boolean
)

// Model cho attachments
data class Attachment(
    @SerializedName("id") val id: Int,
    @SerializedName("fileName") val fileName: String,
    @SerializedName("fileUrl") val fileUrl: String
)
