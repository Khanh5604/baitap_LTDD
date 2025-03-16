package com.example.tuan3_bai2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Hiển thị giao diện MainActivity

        // Chuyển sang OpenActivity sau 3 giây
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OpenActivity::class.java)
            startActivity(intent)
            finish() // Đóng MainActivity
        }, 3000)
    }
}
