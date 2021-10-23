package com.vppanchaofficial.reminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.vppanchaofficial.reminder.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySplashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.let { R.layout.activity_splash_screen })

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },4000)

       /* //Kotlin Coroutines
        Timer().schedule(3000){
            // do something after 3 second
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }*/

    }
}

