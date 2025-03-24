package com.example.tuan3_bai2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class OpenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.open_activity)

        val nextButton: Button = findViewById(R.id.button3)
        nextButton.setOnClickListener {
            val intent = Intent(this, MidActivity::class.java)
            startActivity(intent)
        }
    }
}
