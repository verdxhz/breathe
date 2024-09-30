package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sessione= findViewById<Button>(R.id.sessione)
        sessione.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)}

        val registro= findViewById<Button>(R.id.registro)
        registro.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)}

    }
}