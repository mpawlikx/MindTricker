package com.example.mindtricker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            goToMainActivity()
        }, 3000)

    }

    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)// tworzenie nowej aktywnosci
        startActivity(intent)
        overridePendingTransition(R.anim.fade_out,0)
        finish()
    }

}
