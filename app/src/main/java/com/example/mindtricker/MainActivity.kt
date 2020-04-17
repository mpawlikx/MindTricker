package com.example.mindtricker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* aboutButton.setOnClickListener {
            openAboutActivity()
        }

        exitButton.setOnClickListener {
            finish()
        }*/

    }

    private fun openAboutActivity(){
        val intent=Intent(this,AboutActivity::class.java) //
        startActivity(intent)
    }
}
