package com.example.mindtricker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ranking.*

class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        loadRanking()

        menuButton.setOnClickListener {
            openMainMenu()
        }


    }

    private fun openMainMenu(){
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    private fun loadRanking (){
        val sharedPreferences = getSharedPreferences("player_names", Context.MODE_PRIVATE)
        val name = sharedPreferences.getString("NAME", "")
        val points = sharedPreferences.getInt("POINTS", 0)
        first_score_text.text ="Name: $name     Result: $points"

    }
}
