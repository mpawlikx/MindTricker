package com.example.mindtricker

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mindtricker.GameActivity.Companion.USER_NAME_TAG
import com.example.mindtricker.GameActivity.Companion.USER_POINTS_TAG
import com.example.mindtricker.models.UserModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_ranking.*

class RankingActivity : AppCompatActivity() {
    var recordList = mutableListOf<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        parseExtras()

        menuButton.setOnClickListener {
            openMainMenu()
        }
    }

    private fun parseExtras() {
        val extras = intent.extras
        if (extras != null) {
            loadRanking(extras.getString(USER_NAME_TAG), extras.getInt(USER_POINTS_TAG))
        } else {
            loadRanking(null, null)
        }
    }

    private fun loadRanking(lastName: String?, lastPoints: Int?) {
        val sharedPreferences = getSharedPreferences("player_names", Context.MODE_PRIVATE)
        val rankingJson = sharedPreferences.getString("rankingJson", null)
        val gson = Gson()

        if (rankingJson != null) {
            recordList =
                gson.fromJson(rankingJson, object : TypeToken<MutableList<UserModel>>() {}.type)
        }

        if (lastName != null && lastPoints != null) {
            recordList.add(UserModel(lastName, lastPoints))
            val editor = sharedPreferences.edit()
            editor.putString("rankingJson", gson.toJson(recordList))
            editor.apply()
        }

        recordList.sortByDescending { it.userPoints }
        if (recordList.size >= 1) {
            first_score_text.text = String.format(
                getString(R.string.ranking_row),
                recordList[0].userName,
                recordList[0].userPoints
            )
            if (recordList.size >= 2) {
                second_score_text.text = String.format(
                    getString(R.string.ranking_row),
                    recordList[1].userName,
                    recordList[1].userPoints
                )
                if (recordList.size >= 3) {
                    third_score_text.text = String.format(
                        getString(R.string.ranking_row),
                        recordList[2].userName,
                        recordList[2].userPoints
                    )
                } else {
                    third_score_text.visibility = View.GONE
                }
            } else {
                second_score_text.visibility = View.GONE
                third_score_text.visibility = View.GONE
            }
        } else {
            first_score_text.visibility = View.GONE
            second_score_text.visibility = View.GONE
            third_score_text.visibility = View.GONE
        }
    }

    private fun openMainMenu() {
        finish()
    }
}
