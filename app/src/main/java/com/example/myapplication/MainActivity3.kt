package com.example.myapplication

import android.animation.ObjectAnimator
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Vibrator
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity3 : AppCompatActivity() {

    private lateinit var countDownTimer: CountDownTimer
    private var durata: Int = 0 // Durata predefinita
    private var ripetizioni: Int = 0;
    private lateinit var sharedPreferences: SharedPreferences
    var currentMode: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        sharedPreferences = getSharedPreferences("NomeFilePreferenze", Context.MODE_PRIVATE)
        durata = intent.getIntExtra("velocità", 1) * 2 + 4
        val timer = findViewById<TextView>(R.id.timer)
        timer.text="START"

        Handler().postDelayed({ //ritardo la partenza del timer di 2 secondi
            startTimer(durata)
        }, 2000)

        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val currentDateTime = dateFormat.format(Date())
        if (durata==6)
            currentMode="lenta"
        else
            currentMode="media"
        sharedPreferences.edit().apply {
            clear()
            putString("mode", currentMode)
            putString("timerStartTime", currentDateTime)
            apply()
        }
    }

    private fun startTimer(d: Int) {
        val cerchio = findViewById<ImageView>(R.id.cerchio)
        cerchio.post {
            // Cambia le dimensioni della view con un'animazione
            var scaleX = ObjectAnimator.ofFloat(cerchio, ImageView.SCALE_X, 1.0f, 1.5f)
            var scaleY = ObjectAnimator.ofFloat(cerchio, ImageView.SCALE_Y, 1.0f, 1.5f)
            scaleX.duration = durata.toLong()*1000 // Durata dell'animazione in millisecondi
            scaleY.duration = durata.toLong()*1000 // Durata dell'animazione in millisecondi
            // Avvia le animazioni
            scaleX.start()
            scaleY.start()
            scaleX = ObjectAnimator.ofFloat(cerchio, ImageView.SCALE_X, 1.5f, 1.0f)
            scaleY = ObjectAnimator.ofFloat(cerchio, ImageView.SCALE_Y, 1.5f, 1.0f)
            scaleX.start()
            scaleY.start()
        }
        val azione = findViewById<TextView>(R.id.azione)
        if (ripetizioni % 2 == 0)
            azione.text = "inspira"
        else
            azione.text = "espira"
        countDownTimer = object : CountDownTimer(d.toLong() * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = millisUntilFinished / 1000
                val timer = findViewById<TextView>(R.id.timer)
                val formattedTime = String.format("%02d : %02d", seconds / 60, seconds % 60)
                timer.text = formattedTime
            }

            override fun onFinish() {
                startVibration();
                ripetizioni++
                if (ripetizioni < 20) {
                    startTimer(durata)
                }
            }
        }
        countDownTimer.start()
    }

    private fun startVibration() {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(150)
    }
        override fun onDestroy() {
            super.onDestroy()
            // fermo il timer se l'Activity viene distrutta
            if (::countDownTimer.isInitialized) {
                countDownTimer.cancel()
            }

        }
    }
