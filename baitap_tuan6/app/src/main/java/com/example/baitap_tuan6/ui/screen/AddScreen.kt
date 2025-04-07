package com.example.baitap_tuan6.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baitap_tuan6.viewmodel.ListViewModel
import com.example.baitap_tuan6.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(navController: NavController, viewModel: ListViewModel = viewModel()) {
    var taskTitle by remember { mutableStateOf(TextFieldValue("")) }
    var taskDescription by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = { navController.popBackStack() }) { 
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Blue)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Add New",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Blue,
                            modifier = Modifier.weight(1f)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                // Task Title Field
                Column {
                    Text(
                        text = "Task",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = taskTitle,
                        onValueChange = { taskTitle = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFE3F2FD), RoundedCornerShape(8.dp)),
                        placeholder = { Text("Enter task title") }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Task Description Field
                Column {
                    Text(
                        text = "Description",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    OutlinedTextField(
                        value = taskDescription,
                        onValueChange = { taskDescription = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(Color(0xFFE3F2FD), RoundedCornerShape(8.dp)),
                        placeholder = { Text("Enter task description") }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                // Add Button
                Button(
                    onClick = {
                        if (taskTitle.text.isNotBlank() && taskDescription.text.isNotBlank()) {
                            viewModel.addTask(taskTitle.text, taskDescription.text)
                            navController.popBackStack()
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(0.4f),
                    shape = RoundedCornerShape(14.dp),
                ) {
                    Text("Add", color = Color.White, fontSize = 26.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewAddTaskScreen() {
    AddTaskScreen(navController = rememberNavController())
}
