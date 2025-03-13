package com.example.tuan3_bai1

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class UIComponentsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ui_components)

        val displayLayout = findViewById<LinearLayout>(R.id.display)
        val imageLayout = findViewById<LinearLayout>(R.id.image)
        val inputTextLayout = findViewById<LinearLayout>(R.id.inputText)
        val inputPasswordLayout = findViewById<LinearLayout>(R.id.inputPassword)

        // Nhấn vào "Display" -> Chuyển sang DisplayActivity
        displayLayout.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra("SHOW_TEXT", true)
            startActivity(intent)
        }

        // Nhấn vào "Image" -> Chuyển sang DisplayActivity
        imageLayout.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra("SHOW_TEXT", false)
            startActivity(intent)
        }

        // Nhấn vào "Input Text" -> Chuyển sang InputActivity
        inputTextLayout.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            intent.putExtra("SHOW_INPUT_TEXT", true) // Hiển thị ô nhập text
            startActivity(intent)
        }

        // Nhấn vào "Input Password" -> Chuyển sang InputActivity
        inputPasswordLayout.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            intent.putExtra("SHOW_INPUT_TEXT", false) // Hiển thị ô nhập mật khẩu
            startActivity(intent)
        }
    }
}
