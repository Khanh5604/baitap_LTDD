package com.example.baitap_tuan6.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitap_tuan6.ui.screen.AddTaskScreen
import com.example.baitap_tuan6.ui.screen.ListScreen

@Composable
fun AppNavHost() {
    // Tạo NavController
    val navController = rememberNavController()

    // Định nghĩa NavHost với các route
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            ListScreen(navController = navController) // Màn hình danh sách
        }
        composable("add_task") {
            AddTaskScreen(navController = navController) // Màn hình thêm task
        }
    }
}
