package com.example.mindtricker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        textViewVer.text = String.format(
            getString(R.string.about_activity_text),
            BuildConfig.VERSION_NAME
        )
    }
}
