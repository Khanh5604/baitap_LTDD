package com.example.baitap2tuan4.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.baitap2tuan4.ui.screen.DetailScreen
import com.example.baitap2tuan4.ui.screen.LazyScreen
import com.example.baitap2tuan4.ui.screen.Openscreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "Openscreen") {
        composable("Openscreen") {
            Openscreen(navController)
        }
        composable("LazyScreen") {
            LazyScreen(navController)
        }
        composable("DetailScreen") {
            DetailScreen(navController)
        }
    }
}
