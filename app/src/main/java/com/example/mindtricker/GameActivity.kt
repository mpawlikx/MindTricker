package com.example.mindtricker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import java.security.SecureRandom

class GameActivity : AppCompatActivity() {

    private lateinit var colorNames: Array<String>
    private lateinit var colorValues: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initializeAvailableColors()

        gameText.setOnClickListener {
            Toast.makeText(this, SecureRandom().nextBoolean().toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initializeAvailableColors() {
        colorNames = arrayOf(
            resources.getString(R.string.white), resources.getString(R.string.blue),
            resources.getString(R.string.green), resources.getString(R.string.red)
        )

        colorValues = arrayOf(R.color.white, R.color.blue, R.color.green, R.color.red)
    }

    private fun randomizeValues (){
       // val randomNumber: Int = Math.random()

        if(SecureRandom().nextBoolean()) {

        } else {

        }

    }

    private fun setTextValues(colorName: String, color: Int) {
        gameText.text = colorName
        gameText.setTextColor(color)
    }

}
// niebieski, zielony, czerwony, pomarańczowy, różowy, żółty, czarny