package com.example.bt2_tuan5.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.example.bt2_tuan5.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val firebaseAuth = remember { FirebaseAuth.getInstance() }
    val user = firebaseAuth.currentUser

    LaunchedEffect(user) {
        if (user == null) {
            navController.navigate("login") {
                popUpTo("profile") { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    if (user == null) return

    val userName = user.displayName ?: "Unknown"
    val userEmail = user.email ?: "Unknown"
    val userPhoto = user.photoUrl?.toString()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Nút Back
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Blue)
        }

        Text(text = "Profile", fontSize = 22.sp, color = Color.Blue)
        Spacer(modifier = Modifier.height(16.dp))

        // Ảnh đại diện
        Box(contentAlignment = Alignment.BottomEnd) {
            AsyncImage(
                model = userPhoto,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            IconButton(
                onClick = { /* Chức năng thay đổi ảnh */ },
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.White, shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = "Change Profile Picture",
                    tint = Color.Blue
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Name", fontSize = 16.sp)
            OutlinedTextField(
                value = userName,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Email", fontSize = 16.sp)
            OutlinedTextField(
                value = userEmail,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                readOnly = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Date of Birth", fontSize = 16.sp)
            OutlinedTextField(
                value = "23/05/1995", // Giá trị tĩnh, có thể thay đổi khi lấy từ DB
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Dropdown")
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                firebaseAuth.signOut()
                navController.navigate("login") {
                    popUpTo("profile") { inclusive = true }
                    launchSingleTop = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text("Back", color = Color.White, fontSize = 16.sp)
        }
    }
}
