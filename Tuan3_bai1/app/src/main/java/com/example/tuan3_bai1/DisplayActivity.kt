package com.example.tuan3_bai1


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val layoutText = findViewById<LinearLayout>(R.id.layoutText)
        val imageContent = findViewById<ImageView>(R.id.imageContent)

        // Nhận dữ liệu từ Intent
        val showText = intent.getBooleanExtra("SHOW_TEXT", true)

        if (showText) {
            layoutText.visibility = View.VISIBLE
            imageContent.visibility = View.GONE
        } else {
            layoutText.visibility = View.GONE
            imageContent.visibility = View.VISIBLE
        }
    }
}
