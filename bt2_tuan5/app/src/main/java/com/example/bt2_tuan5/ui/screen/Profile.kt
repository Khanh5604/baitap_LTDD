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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.bt2_tuan5.R
import com.example.bt2_tuan5.viewmodel.ProfileViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = viewModel()) {
    val userProfile by viewModel.userProfile.collectAsState()
    val navigateToLogin by viewModel.navigateToLogin.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val successMessage by viewModel.successMessage.collectAsState()

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    LaunchedEffect(navigateToLogin) {
        if (navigateToLogin) {
            navController.navigate("login") {
                popUpTo("profile") { inclusive = true }
                launchSingleTop = true
            }
            viewModel.resetNavigation()
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize().background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.Blue)
        }
        return
    }

    val profile = userProfile ?: return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(text = "Hồ sơ", fontSize = 22.sp, color = Color.Blue)
        Spacer(modifier = Modifier.height(16.dp))

        // Hiển thị thông báo lỗi hoặc thành công
        errorMessage?.let {
            Text(text = it, color = Color.Red, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
        }
        successMessage?.let {
            Text(text = it, color = Color.Green, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
            LaunchedEffect(it) {
                kotlinx.coroutines.delay(2000) // Hiển thị 2 giây
                viewModel.clearMessages()
            }
        }

        Box(contentAlignment = Alignment.BottomEnd) {
            AsyncImage(
                model = profile.photoUrl,
                contentDescription = "Ảnh đại diện",
                modifier = Modifier.size(100.dp).clip(CircleShape).background(Color.Gray)
            )
            IconButton(
                onClick = { /* TODO: Triển khai thay đổi ảnh */ },
                modifier = Modifier.size(24.dp).background(Color.White, shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = "Thay đổi ảnh đại diện",
                    tint = Color.Blue
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text("Tên", fontSize = 16.sp)
            OutlinedTextField(
                value = profile.name,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Email", fontSize = 16.sp)
            OutlinedTextField(
                value = profile.email,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                readOnly = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text("Ngày sinh", fontSize = 16.sp)
            OutlinedTextField(
                value = profile.dateOfBirth,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Chọn ngày")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { viewModel.saveCurrentDateOfBirth() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text("Xác nhận lưu", color = Color.White)
        }
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { viewModel.signOut() },
            modifier = Modifier.fillMaxWidth().height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text("Đăng xuất", color = Color.White, fontSize = 16.sp)
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDate = datePickerState.selectedDateMillis
                        if (selectedDate != null) {
                            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                            val dateString = dateFormat.format(Date(selectedDate))
                            viewModel.updateDateOfBirth(dateString)
                        }
                        showDatePicker = false
                    }
                ) {
                    Text("Xác nhận")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text("Hủy")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}