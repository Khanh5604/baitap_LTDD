package com.example.baitap1tuan4.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.baitap1tuan4.ui.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(navController: NavController, viewModel: TaskViewModel = viewModel()) {
    val taskList by viewModel.taskList.collectAsState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Tasks") }) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            if (taskList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Không có dữ liệu", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(taskList) { task ->
                        TaskItem(task, onClick = {
                            navController.navigate("taskDetail/${task.id}")
                        })
                    }
                }
            }
        }
    }
}
