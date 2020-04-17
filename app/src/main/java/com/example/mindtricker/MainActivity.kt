package com.example.mindtricker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        startButton.setOnClickListener {
            openGameActivity()
        }

        rankingButton.setOnClickListener {
            Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }

        settingsButton.setOnClickListener {
            Toast.makeText(this, "Not implemented yet", Toast.LENGTH_SHORT).show()
        }

        aboutButton.setOnClickListener {
            openAboutActivity()
        }

        exitButton.setOnClickListener {
            finish()
        }


    }

    private fun openGameActivity() {
        val intent = Intent(this, GameActivity::class.java)
        startActivity(intent)
    }

    private fun openAboutActivity() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }
}
