package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity4 : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
       sharedPreferences = getSharedPreferences("NomeFilePreferenze", Context.MODE_PRIVATE)
        val data= sharedPreferences.getString("timerStartTime", "")
        Log.d("MainActivity4", "Valore recuperato dalle SharedPreferences: $data")
        val n = findViewById<TextView>(R.id.data)
        n.setText(data)
        }

}