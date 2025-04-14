package com.example.tuan7_btvn.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tuan7_btvn.MyApplication
import com.example.tuan7_btvn.model.Task
import com.example.tuan7_btvn.viewmodel.TaskViewModel
import com.example.tuan7_btvn.viewmodel.TaskViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavHostController) {
    val context = LocalContext.current
    val app = context.applicationContext as MyApplication
    val taskDao = app.database.taskDao()
    val viewModel: TaskViewModel = viewModel(factory = TaskViewModelFactory(taskDao))

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Text("<", fontSize = 24.sp)
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Tiêu đề") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Mô tả") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        val task = Task(title = title, description = description)
                        viewModel.addTask(task)
                        navController.navigateUp()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Thêm", fontSize = 16.sp)
            }
        }
    }
}