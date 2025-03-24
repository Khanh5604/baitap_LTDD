package com.example.baitap1tuan4.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitap1tuan4.ui.screen.TaskDetailScreen
import com.example.baitap1tuan4.ui.screen.TaskScreen

@Composable
fun AppNavGraph(navController: NavHostController) { // ✅ Nhận NavController làm tham số
    NavHost(
        navController = navController,
        startDestination = "taskList",

    ) {
        composable("taskList") {
            TaskScreen(navController)
        }
        composable("taskDetail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            TaskDetailScreen(taskId)
        }
    }
}
