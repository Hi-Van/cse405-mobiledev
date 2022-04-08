package com.example.lab2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lab2.controller.QTroller
import com.example.lab2.model.ScoreTracker

class MainActivity : AppCompatActivity() {

    private lateinit var scoreButton: Button
    private lateinit var nextButton: Button
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var randomButton: Button

    private lateinit var qPrompt: TextView

    private var scoreTracker: ScoreTracker = ScoreTracker()
    private var qTroller: QTroller = QTroller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scoreButton = findViewById(R.id.scorebutton)
        nextButton = findViewById(R.id.nextbutton)
        trueButton = findViewById(R.id.truebutton)
        falseButton = findViewById(R.id.falsebutton)
        randomButton = findViewById(R.id.randombutton)

        qPrompt = findViewById(R.id.questionprompt)


        qPrompt.setText(qTroller.currQuestion())

        scoreButton.setOnClickListener {
            Intent(baseContext, ScoreActivity::class.java).also { scoreActivity ->
                scoreActivity.putExtra("FROM_MAIN", scoreTracker.score().toString())
                startActivity(scoreActivity)

            }

            scoreTracker.reset()
        }

        nextButton.setOnClickListener {
            val str = qTroller.nextQuestion()

            qPrompt.setText(str)
        }

        randomButton.setOnClickListener {
            val str = qTroller.randomQuestion()

            qPrompt.setText(str)
        }

        trueButton.setOnClickListener {
            val ans = qTroller.currAnswer()

            if (ans) {
                scoreTracker.inc()
            }

            else {
                scoreTracker.dec()
            }

            val str = qTroller.nextQuestion()

            qPrompt.setText(str)
        }

        falseButton.setOnClickListener {
            val ans = qTroller.currAnswer()

            if (!ans) {
                scoreTracker.inc()
            }

            else {
                scoreTracker.dec()
            }

            val str = qTroller.nextQuestion()

            qPrompt.setText(str)
        }
    }
}