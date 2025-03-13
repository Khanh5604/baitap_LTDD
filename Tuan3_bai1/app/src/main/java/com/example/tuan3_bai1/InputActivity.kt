package com.example.tuan3_bai1

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val inputText = findViewById<EditText>(R.id.inputText)
        val inputPassword = findViewById<EditText>(R.id.inputPassword)

        // Nhận dữ liệu từ Intent
        val showInputText = intent.getBooleanExtra("SHOW_INPUT_TEXT", true)

        if (showInputText) {
            inputText.visibility = View.VISIBLE
            inputPassword.visibility = View.GONE
        } else {
            inputText.visibility = View.GONE
            inputPassword.visibility = View.VISIBLE
        }
    }
}
