package com.example.mindtricker

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_game.*
import java.security.SecureRandom
import kotlin.math.abs

class GameActivity : AppCompatActivity() {

    private lateinit var colorNames: Array<String>
    private lateinit var colorValues: Array<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initializeAvailableColors()

        randomizeValues()

        gameText.setOnClickListener {
//            Toast.makeText(this, SecureRandom().nextBoolean().toString(), Toast.LENGTH_SHORT).show()
            randomizeValues()
        }
    }

    private fun initializeAvailableColors() {
        colorNames = arrayOf(
            resources.getString(R.string.white), resources.getString(R.string.blue),
            resources.getString(R.string.green), resources.getString(R.string.red)
        )

        colorValues = arrayOf(
            resources.getColor(R.color.white), resources.getColor(R.color.blue),
            resources.getColor(R.color.green), resources.getColor(R.color.red)
        )
    }

    private fun randomizeValues() {
        if (SecureRandom().nextBoolean()) {
            val randomIndex = abs(SecureRandom().nextInt()) % 4
            setTextValues(colorNames[randomIndex], colorValues[randomIndex])
        } else {
            val firstIndex = abs(SecureRandom().nextInt()) % 4
            var secondIndex: Int
            
            do {
                secondIndex = abs(SecureRandom().nextInt()) % 4
            } while (secondIndex == firstIndex)

            setTextValues(colorNames[firstIndex], colorValues[secondIndex])
        }

    }

    private fun setTextValues(text: String, color: Int) {
        gameText.text = text
        gameText.setTextColor(color)
    }

}
// niebieski, zielony, czerwony, pomarańczowy, różowy, żółty, czarny