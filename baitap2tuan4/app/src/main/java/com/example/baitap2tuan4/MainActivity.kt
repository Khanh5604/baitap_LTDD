package com.example.baitap2tuan4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.baitap2tuan4.ui.screen.DetailScreen
import com.example.baitap2tuan4.ui.screen.LazyScreen
import com.example.baitap2tuan4.ui.screen.Openscreen
import com.example.baitap2tuan4.ui.theme.Baitap2tuan4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Baitap2tuan4Theme {
                val navController = rememberNavController()  // Tạo NavController
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Đảm bảo sử dụng innerPadding trong NavHost
                    NavHost(
                        navController = navController,
                        startDestination = "Openscreen",
                        modifier = Modifier.padding(innerPadding)  // Thêm padding vào NavHost
                    ) {
                        composable("Openscreen") {
                            Openscreen(navController = navController)  // Gọi Openscreen
                        }
                        composable("LazyScreen") {
                            LazyScreen(navController = navController)  // Gọi LazyScreen
                        }
                        composable("DetailScreen") {
                            DetailScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Baitap2tuan4Theme {
        val navController = rememberNavController()
        // Cấu hình NavHost trong preview
        NavHost(
            navController = navController,
            startDestination = "Openscreen",
            modifier = Modifier.padding(16.dp)  // Áp dụng padding nếu cần trong preview
        ) {
            composable("Openscreen") {
                Openscreen(navController = navController)
            }
            composable("LazyScreen") {
                LazyScreen(navController = navController)
            }
            composable("DetailScreen") {
                DetailScreen(navController = navController)
            }
        }
    }
}
