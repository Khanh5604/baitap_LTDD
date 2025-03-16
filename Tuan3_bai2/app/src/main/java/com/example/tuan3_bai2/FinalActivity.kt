package com.example.tuan3_bai2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.final_activity)

        val backButton: ImageButton = findViewById(R.id.imageButton2)
        backButton.setOnClickListener {
            val intent = Intent(this, MidActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.left_in,R.anim.right_out)
            finish()
        }
    }
}