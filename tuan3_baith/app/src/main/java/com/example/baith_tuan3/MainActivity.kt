package com.example.baith_tuan3


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

        var toggleState = false

        buttonSayHi.setOnClickListener {
            if (toggleState) {
                textMessage.text = "Hi!"
            } else {
                textMessage.text = "I'm Nguyễn Phi Khanh"
            }
            toggleState = !toggleState  
        }
    }
}
