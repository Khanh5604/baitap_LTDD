package com.example.baitapth_tuan7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.baitapth_tuan7.ui.screen.ThemeScreen
import com.example.baitapth_tuan7.ui.viewmodel.ThemeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: ThemeViewModel = viewModel()
            ThemeScreen(viewModel = viewModel)
        }
    }
}
