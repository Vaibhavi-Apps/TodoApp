package com.vppanchalofficial.reminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.vppanchalofficial.reminder.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySplashScreenBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.let { R.layout.activity_splash_screen })
        val preference = PreferenceHelper(this)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            if(!preference.getBooleanData(Constant.FLAG_NEW_USER)){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
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

