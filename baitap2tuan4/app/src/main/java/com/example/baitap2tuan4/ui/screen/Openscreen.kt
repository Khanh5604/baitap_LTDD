package com.example.baitap2tuan4.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.baitap2tuan4.R

@Composable
fun Openscreen(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(), // Áp dụng modifier vào Scaffold
        containerColor = Color.White
    ) { paddingValues -> // Lấy padding từ Scaffold
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues) // Sử dụng paddingValues ở đây
                .padding(horizontal = 24.dp, vertical = 40.dp), // Đảm bảo có padding ở các phía
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .size(180.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.anh1),
                    contentDescription = "Navigation Logo",
                    modifier = Modifier.size(160.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Navigation",
                fontSize = 24.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "is a framework that simplifies the implementation of navigation between different UI components (activities, fragments, or composables) in an app",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    navController.navigate("LazyScreen") // Đảm bảo đường dẫn này là đúng
                },
                modifier = Modifier
                    .fillMaxWidth(0.85f)
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "PUSH", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Openscreen(navController = rememberNavController())
}
