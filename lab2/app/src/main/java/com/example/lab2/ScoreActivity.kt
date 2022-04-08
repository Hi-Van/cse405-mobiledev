package com.example.lab2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ScoreActivity : AppCompatActivity() {

    private lateinit var scoreView: TextView
    private lateinit var returnButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        scoreView = findViewById(R.id.score)
        returnButton = findViewById(R.id.returnbutton)

        intent?.let {
            val str = it.getStringExtra("FROM_MAIN")
            scoreView.text = str
        }

        returnButton.setOnClickListener {
            Intent(baseContext, MainActivity::class.java).also { mainActivity ->
                startActivity(mainActivity)
            }
        }

    }
}