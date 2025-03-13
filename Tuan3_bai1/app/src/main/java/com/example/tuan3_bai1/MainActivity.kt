package com.example.tuan3_bai1



import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Tìm Button
        val btnReady = findViewById<Button>(R.id.btnReady)

        // Xử lý sự kiện khi nhấn nút
        btnReady.setOnClickListener {
            val intent = Intent(this, UIComponentsActivity::class.java)
            startActivity(intent)
        }

    }
}
