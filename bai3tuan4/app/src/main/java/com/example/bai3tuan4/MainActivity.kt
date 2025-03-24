package com.example.bai3tuan4

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.bai3tuan4.ui.theme.Bai3tuan4Theme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// Data Model
data class TaskResponse(
    val isSuccess: Boolean,
    val message: String,
    val data: List<Task>
)

data class Task(
    val id: Int,
    val title: String,
    val desImageURL: String,
    val description: String,
    val status: String
)

// API Service
interface TaskApiService {
    @GET("tasks")
    suspend fun getTasks(): TaskResponse
}

// Retrofit Instance
object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://amock.io/api/researchUTH/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: TaskApiService by lazy { retrofit.create(TaskApiService::class.java) }
}

// ViewModel
class TaskViewModel : ViewModel() {
    var tasks by mutableStateOf<List<Task>>(emptyList()) // ✅ Đổi thành danh sách
        private set
    var isLoading by mutableStateOf(true)
        private set

    init {
        fetchTasks()
    }

    private fun fetchTasks() {
        viewModelScope.launch {
            try {
                isLoading = true
                val response = RetrofitInstance.api.getTasks()
                Log.d("API_RESPONSE", "Response: $response")

                if (response.isSuccess && response.data.isNotEmpty()) {
                    tasks = response.data
                    Log.d("API_SUCCESS", "Fetched tasks: $tasks")
                } else {
                    Log.e("API_ERROR", "API response error: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error fetching task", e)
            } finally {
                delay(1000)
                isLoading = false
            }
        }
    }
}

// Main Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: TaskViewModel = viewModel()
            Bai3tuan4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaskScreen(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }
}

// UI - Task Screen
@Composable
fun TaskScreen(modifier: Modifier = Modifier, viewModel: TaskViewModel) {
    val tasks = viewModel.tasks
    val isLoading = viewModel.isLoading

    Column(
        modifier = modifier.fillMaxSize().padding(8.dp), // Giảm padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("SmartTasks", fontSize = 20.sp) // Giảm font size
        Spacer(modifier = Modifier.height(8.dp))

        when {
            isLoading -> {
                CircularProgressIndicator()
            }
            tasks.isEmpty() -> {
                Image(
                    painter = painterResource(id = R.drawable.anh3),
                    contentDescription = "No Tasks",
                    modifier = Modifier.fillMaxWidth().height(200.dp) // Giảm chiều cao
                )
            }
            else -> {
                // ✅ Hiển thị danh sách Task
                LazyColumn {
                    items(tasks) { task ->
                        TaskCard(task)
                        Spacer(modifier = Modifier.height(4.dp)) 
                    }
                }
            }
        }
    }
}

// UI - Task Card
@Composable
fun TaskCard(task: Task) {
    val statusColor = when (task.status) {
        "In Progress" -> Color(0xFFD9A7A7)
        "Pending" -> Color(0xFFD9E8A7)
        else -> Color(0xFFA7D9E8)
    }

    Card(
        modifier = Modifier.fillMaxWidth().padding(4.dp), // Thêm padding để gọn hơn
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(modifier = Modifier.background(statusColor).padding(8.dp)) { // Giảm padding
            Text(task.title, fontSize = 16.sp) // Giảm font size
            Spacer(modifier = Modifier.height(2.dp))
            Text(task.description, fontSize = 12.sp) // Giảm font size
            Spacer(modifier = Modifier.height(2.dp))
            Text("Status: ${task.status}", fontSize = 12.sp)

            AsyncImage(
                model = task.desImageURL,
                contentDescription = "Task Image",
                modifier = Modifier.fillMaxWidth().height(120.dp).padding(top = 4.dp) // Giảm chiều cao
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskScreen() {
    Bai3tuan4Theme {
        TaskScreen(viewModel = TaskViewModel())
    }
}
