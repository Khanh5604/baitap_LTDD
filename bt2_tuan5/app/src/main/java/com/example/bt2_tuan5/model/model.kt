package com.example.bt2_tuan5.model

data class UserProfile(
    val name: String = "Không xác định",
    val email: String = "Không xác định",
    val photoUrl: String? = null,
    val dateOfBirth: String = ""
)