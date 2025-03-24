package com.example.baitap1tuan4.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.baitap1tuan4.data.model.Task
import com.example.baitap1tuan4.ui.viewmodel.TaskViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(taskId: Int?, viewModel: TaskViewModel = viewModel()) {
    val taskList by viewModel.taskList.collectAsState() // Theo dõi danh sách Task
    val task = taskList.find { it.id == taskId } // Tìm Task từ danh sách

    Scaffold(
        topBar = { TopAppBar(title = { Text("Task Detail") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (task != null) {
                Text(text = "Title: ${task.title}", style = MaterialTheme.typography.headlineSmall)
                Text(text = "Description: ${task.description}", style = MaterialTheme.typography.bodyLarge)
                Text(text = "Status: ${task.status}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Priority: ${task.priority}", style = MaterialTheme.typography.bodyMedium)
            } else {
                Text(text = "Không tìm thấy Task", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
