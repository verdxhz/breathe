package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val lenta = findViewById<Button>(R.id.lenta)
        lenta.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java).putExtra("velocità", 1)
            startActivity(intent)
        }

        val media = findViewById<Button>(R.id.media)
        media.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java).putExtra("velocità", 0)
            startActivity(intent)
        }
    }


}

