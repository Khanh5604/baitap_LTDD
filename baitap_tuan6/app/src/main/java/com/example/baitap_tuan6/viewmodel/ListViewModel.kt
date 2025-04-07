package com.example.baitap_tuan6.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.baitap_tuan6.model.Task

class ListViewModel : ViewModel() {

    private val _tasks = mutableStateOf<List<Task>>(emptyList())
    val tasks: List<Task> get() = _tasks.value

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            // Dữ liệu mẫu với màu nền dưới dạng Long
            _tasks.value = listOf(
                Task("Complete Android Project", "Finish the UI, integrate API, and write documentation", 0xFFBBDEFB),
                Task("Complete Android Project", "Finish the UI, integrate API, and write documentation", 0xFFF8BBD0),
                Task("Complete Android Project", "Finish the UI, integrate API, and write documentation", 0xFFDCE775)
            )
        }
    }

    fun addTask(title: String, description: String) {
        // Đảm bảo truyền backgroundColor khi tạo task mới
        val newTask = Task(title, description, 0xFFFFFFFF) // Màu nền mặc định
        _tasks.value = _tasks.value + newTask
    }
}
