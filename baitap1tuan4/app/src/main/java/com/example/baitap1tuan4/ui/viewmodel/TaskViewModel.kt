package com.example.baitap1tuan4.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baitap1tuan4.data.model.Task
import com.example.baitap1tuan4.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()

    private val _taskList = MutableStateFlow<List<Task>>(emptyList())
    val taskList = _taskList.asStateFlow()

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask = _selectedTask.asStateFlow()

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            _taskList.value = repository.getTasks()
        }
    }

    // ✅ Lấy task theo ID
    fun getTaskById(taskId: Int?): Task? {
        return _taskList.value.find { it.id == taskId }
    }

    fun selectTask(task: Task) {
        _selectedTask.value = task
    }

    fun clearSelection() {
        _selectedTask.value = null
    }
}
