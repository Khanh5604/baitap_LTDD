package com.example.baitapth_tuan7.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.baitapth_tuan7.ui.model.ThemeOption
import com.example.baitapth_tuan7.ui.viewmodel.ThemeViewModel

@Composable
fun ThemeScreen(viewModel: ThemeViewModel) {
    val context = LocalContext.current

    val selectedTheme by viewModel.selectedTheme.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = selectedTheme.color
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Setting",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Choosing the right theme sets the tone and personality of your app",
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                ThemeOption.values().forEach { theme ->
                    Box(
                        modifier = Modifier
                            .size(70.dp, 40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(theme.color)
                            .border(
                                width = if (theme == selectedTheme) 3.dp else 1.dp,
                                color = if (theme == selectedTheme) Color.White else Color.Gray,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable { viewModel.selectTheme(theme) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {

                    viewModel.applyTheme()
                    Toast.makeText(context, "Áp dụng giao diện thành công", Toast.LENGTH_SHORT).show()
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.width(200.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text("Apply", color = selectedTheme.color, fontWeight = FontWeight.Bold)
            }
        }
    }
}
