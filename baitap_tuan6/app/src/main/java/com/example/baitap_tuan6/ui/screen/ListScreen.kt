package com.example.baitap_tuan6.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baitap_tuan6.viewmodel.ListViewModel
import com.example.baitap_tuan6.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController, viewModel: ListViewModel = viewModel()) {
    val tasks by remember { mutableStateOf(viewModel.tasks) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = { /* Back */ }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.Blue)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "List",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue,
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(onClick = { /* Add */ }) {
                            Icon(Icons.Default.AddCircle, contentDescription = null, tint = Color.Red)
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    selected = true, onClick = {}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.DateRange, contentDescription = null) },
                    selected = false, onClick = {}
                )
                NavigationBarItem(
                    icon = {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(Color(0xFF2196F3), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Add, contentDescription = null, tint = Color.White)
                        }
                    },
                    selected = false, onClick = {}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.List, contentDescription = null) },
                    selected = false, onClick = {}
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                    selected = false, onClick = {}
                )
            }
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                tasks.forEach { task ->
                    TaskItem(task = task, onClick = {
                        // Khi nhấn vào task, điều hướng đến AddTaskScreen
                        navController.navigate("add_task")
                    })
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}

@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(task.backgroundColor), RoundedCornerShape(12.dp))
            .padding(12.dp)
            .clickable(onClick = onClick) // Thêm sự kiện nhấn vào task
    ) {
        Text(text = task.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = task.description, fontSize = 14.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewListScreen() {
    val navController = rememberNavController()
    ListScreen(navController = navController)
}
