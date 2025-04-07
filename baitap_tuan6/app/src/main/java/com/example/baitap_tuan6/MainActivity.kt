package com.example.baitap_tuan6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.baitap_tuan6.navigation.AppNavHost
import com.example.baitap_tuan6.ui.theme.Baitap_tuan6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Baitap_tuan6Theme {
                // Đảm bảo rằng AppNavHost được gọi trong Content
                Surface(color = MaterialTheme.colorScheme.background) {
                    AppNavHost() // Gọi NavHost để điều hướng
                }
            }
        }
    }
}
