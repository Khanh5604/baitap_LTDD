package com.example.bt2_tuan5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bt2_tuan5.ui.screen.LoginScreen
import com.example.bt2_tuan5.ui.screen.ProfileScreen
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val firebaseAuth = FirebaseAuth.getInstance()
            val user = firebaseAuth.currentUser

            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = if (user != null) "profile" else "login"
                    ) {
                        composable("login") {
                            LoginScreen(navController)
                        }
                        composable("profile") {
                            ProfileScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
