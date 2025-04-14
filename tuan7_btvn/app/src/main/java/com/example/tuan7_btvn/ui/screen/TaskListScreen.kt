package com.example.tuan7_btvn.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun TaskListScreen(navController: NavHostController) {
    val context = LocalContext.current
    val app = context.applicationContext as MyApplication
    val taskDao = app.database.taskDao()
    val viewModel: TaskViewModel = viewModel(factory = TaskViewModelFactory(taskDao))
    val tasks = viewModel.tasks.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh Sách Công Việc") },
                actions = {
                    IconButton(onClick = { navController.navigate("add_task") }) {
                        Text("+", fontSize = 24.sp)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("add_task") },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("+", fontSize = 24.sp)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tasks.value) { task ->
                TaskItem(task)
            }
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE6F0FA)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = task.title, fontSize = 18.sp, color = Color.Black)
            Text(text = task.description, fontSize = 14.sp, color = Color.Gray)
        }
    }
}