package com.example.baitap2tuan4.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyScreen(navController: NavController) {
    val items = remember { List(100) { index -> "${index + 1} | The only way to do great work is to love what you do." } }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "LazyColumn",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Magenta
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Blue)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            itemsIndexed(items) { index, item ->
                ListItem(itemText = item, onClick = {
                    navController.navigate("DetailScreen")
                })
                if (index < items.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp)) // Cách giữa các item
                }
            }
        }
    }
}

@Composable
fun ListItem(itemText: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
            .clickable { onClick() }, // Tạo sự kiện khi click vào toàn bộ item
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = itemText,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color.Black, shape = CircleShape)
                .clickable { onClick() }, // Mũi tên vẫn có thể click
            contentAlignment = Alignment.Center
        ) {
            Text(text = ">", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLazyScreen() {
    val fakeNavController = rememberNavController() // Fake NavController để tránh lỗi
    LazyScreen(navController = fakeNavController)
}
