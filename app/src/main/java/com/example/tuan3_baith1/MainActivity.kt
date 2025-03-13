package com.example.tuan3_baith1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val textMessage = findViewById<TextView>(R.id.textMessage)
        val buttonSayHi = findViewById<Button>(R.id.buttonSayHi)

        var toggleState = false  // Biến để kiểm tra trạng thái

        buttonSayHi.setOnClickListener {
            if (toggleState) {
                textMessage.text = "Hi!"
            } else {
                textMessage.text = "I'm Nguyễn Phi Khanh"
            }
            toggleState = !toggleState  // Đảo trạng thái
        }
    }
}
