package com.example.mindtricker

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.IntegerRes
import kotlinx.android.synthetic.main.activity_game.*
import java.security.SecureRandom
import kotlin.math.abs

class GameActivity : AppCompatActivity() {

    private lateinit var colorNames: Array<String>
    private lateinit var colorValues: Array<Int>
    private var points = 0
    private var isCorrect: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initializeAvailableColors()


        splashTimer()

        correctButton.setOnClickListener {
            correctButtonActivate()
        }
        wrongButton.setOnClickListener {
            wrongButtonActivate()
        }

        //  gameText.setOnClickListener {
//            Toast.makeText(this, SecureRandom().nextBoolean().toString(), Toast.LENGTH_SHORT).show()
        //  }
    }

    private fun splashTimer() {

        val timer = object : CountDownTimer(3000, 10) {
            override fun onTick(millisUntilFinished: Long) {
                gameText.text = "${(millisUntilFinished / 1000)+1} ..."
            }

            override fun onFinish() {
                initTimer()
                randomizeValues()
                wrongButton.visibility= View.VISIBLE
                correctButton.visibility= View.VISIBLE
            }
        }
        timer.start()
    }

    private fun initTimer() {

        val timer = object : CountDownTimer(10000, 10) {
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "${millisUntilFinished / 1000}:${millisUntilFinished % 1000}"
            }

            override fun onFinish() {
                Toast.makeText(
                    this@GameActivity,
                    SecureRandom().nextBoolean().toString(),
                    Toast.LENGTH_SHORT
                ).show()
                timeText.text = "0:0"
                gameText.visibility= View.INVISIBLE // todo komunikat o ilości punktów
                endGameText.visibility =View.VISIBLE
                wrongButton.visibility= View.INVISIBLE
                correctButton.visibility= View.INVISIBLE

                rankingSystem()

            }
        }
        timer.start()
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
        isCorrect=SecureRandom().nextBoolean()
        if (isCorrect) {
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

    private fun correctButtonActivate() {
        if (isCorrect) {
            points++
            pointsText.text = "$points"
        } else {
            points -= 2
            pointsText.text = "$points"
        }
        randomizeValues()
    }



    private fun wrongButtonActivate() {
        if (isCorrect) {
            points -= 2
            pointsText.text = "$points"
        } else {
            points++
            pointsText.text = "$points"
        }
        randomizeValues()
    }


    public fun rankingSystem() {
        playerNameEditText.visibility= View.VISIBLE
        saveButton.visibility= View.VISIBLE
        val sharedPreferences = getSharedPreferences("player_names", Context.MODE_PRIVATE)
        saveButton.setOnClickListener {
            val name = playerNameEditText.text.toString().trim()
            val points = Integer.parseInt(pointsText.text.toString().trim())
            val editor = sharedPreferences.edit()
            editor.putString("NAME", name)
            editor.putInt("POINTS", points)
            editor.apply()
        }
        show_info_button.setOnClickListener {
            val name = sharedPreferences.getString("NAME", "")
            val points = sharedPreferences.getInt("POINTS", 0)
            test_text.text = "Name: $name\nPoints: $points"
        }
    }
}
// niebieski, zielony, czerwony, pomarańczowy, różowy, żółty, czarny