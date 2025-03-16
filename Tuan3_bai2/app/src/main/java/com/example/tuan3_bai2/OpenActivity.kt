package com.example.tuan3_bai2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OpenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.open_activity) // Giao diện OpenActivity

        // Lấy nút Next bằng ID
        val nextButton: Button = findViewById(R.id.button3)

        // Bắt sự kiện khi nhấn Next
        nextButton.setOnClickListener {
            val intent = Intent(this, MidActivity::class.java)
            startActivity(intent) // Chuyển sang MidActivity
        }
    }
}
