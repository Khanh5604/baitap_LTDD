package com.example.tuan3_bai2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MidActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mid_activity)

        val nextButton: Button = findViewById(R.id.button3)

        nextButton.setOnClickListener {
            val intent = Intent(this, FinalActivity::class.java)
            startActivity(intent)
        }
        val backButton: ImageButton = findViewById(R.id.imageButton2)

        backButton.setOnClickListener {
            val intent = Intent(this, OpenActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.left_in,R.anim.right_out)
            finish()
        }

    }

}
