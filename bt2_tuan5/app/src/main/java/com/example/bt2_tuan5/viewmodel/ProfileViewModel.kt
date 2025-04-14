package com.example.bt2_tuan5.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bt2_tuan5.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileViewModel : ViewModel() {
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    private val _userProfile = MutableStateFlow<UserProfile?>(null)
    val userProfile: StateFlow<UserProfile?> = _userProfile

    private val _navigateToLogin = MutableStateFlow(false)
    val navigateToLogin: StateFlow<Boolean> = _navigateToLogin

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage: StateFlow<String?> = _successMessage

    init {
        loadUserProfile()
    }

    private fun loadUserProfile() {
        viewModelScope.launch {
            _isLoading.value = true
            val user = firebaseAuth.currentUser
            if (user == null) {
                _navigateToLogin.value = true
                _isLoading.value = false
            } else {
                try {
                    val userId = user.uid
                    val document = firestore.collection("users").document(userId).get().await()
                    val dateOfBirth = document.getString("dateOfBirth") ?: ""
                    _userProfile.value = UserProfile(
                        name = user.displayName ?: "Không xác định",
                        email = user.email ?: "Không xác định",
                        photoUrl = user.photoUrl?.toString(),
                        dateOfBirth = dateOfBirth.ifEmpty { "Chưa có thông tin" }
                    )
                    _errorMessage.value = null
                } catch (e: Exception) {
                    _errorMessage.value = "Không thể tải dữ liệu: ${e.message}"
                    _userProfile.value = UserProfile(
                        name = user.displayName ?: "Không xác định",
                        email = user.email ?: "Không xác định",
                        photoUrl = user.photoUrl?.toString(),
                        dateOfBirth = "Chưa có thông tin"
                    )
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }

    fun updateDateOfBirth(newDateOfBirth: String) {
        viewModelScope.launch {
            val user = firebaseAuth.currentUser
            if (user != null) {
                try {
                    val userId = user.uid
                    firestore.collection("users").document(userId)
                        .set(mapOf("dateOfBirth" to newDateOfBirth), SetOptions.merge()).await()
                    _userProfile.value = _userProfile.value?.copy(dateOfBirth = newDateOfBirth)
                    _errorMessage.value = null
                } catch (e: Exception) {
                    _errorMessage.value = "Không thể lưu ngày sinh: ${e.message}. Vui lòng kiểm tra kết nối mạng."
                }
            } else {
                _errorMessage.value = "Người dùng chưa đăng nhập."
            }
        }
    }

    fun saveCurrentDateOfBirth() {
        viewModelScope.launch {
            val user = firebaseAuth.currentUser
            if (user != null) {
                val currentDateOfBirth = _userProfile.value?.dateOfBirth ?: ""
                if (currentDateOfBirth.isNotEmpty() && currentDateOfBirth != "Chưa có thông tin") {
                    try {
                        val userId = user.uid
                        val name = user.displayName ?: ""
                        val email = user.email ?:""
                        firestore.collection("users").document(userId)
                            .set(
                                mapOf(
                                    "uid" to userId,
                                    "name" to name,
                                    "email" to email,
                                    "dateOfBirth" to currentDateOfBirth
                                ),
                                SetOptions.merge()
                            ).await()
                        _errorMessage.value = null
                        _successMessage.value = "Lưu thông tin thành công!"
                    } catch (e: Exception) {
                        _errorMessage.value = "Không thể lưu thông tin: ${e.message}. Vui lòng kiểm tra kết nối mạng."
                    }
                } else {
                    _errorMessage.value = "Không có ngày sinh để lưu!"
                    _successMessage.value = null
                }
            } else {
                _errorMessage.value = "Người dùng chưa đăng nhập."
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            val user = firebaseAuth.currentUser
            if (user != null) {
                val currentDateOfBirth = _userProfile.value?.dateOfBirth ?: ""
                if (currentDateOfBirth.isNotEmpty() && currentDateOfBirth != "Chưa có thông tin") {
                    try {
                        firestore.collection("users").document(user.uid)
                            .set(mapOf("dateOfBirth" to currentDateOfBirth), SetOptions.merge()).await()
                    } catch (e: Exception) {
                    }
                }
            }
            firebaseAuth.signOut()
            _navigateToLogin.value = true
        }
    }

    fun resetNavigation() {
        _navigateToLogin.value = false
    }

    fun clearMessages() {
        _errorMessage.value = null
        _successMessage.value = null
    }
}