package com.example.k2022_04_22_lab7

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.k2022_04_22_lab7.controllers.QTroller
import com.example.k2022_04_22_lab7.models.questions.Question
import com.example.k2022_04_22_lab7.models.score.ScoreViewModel
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    private lateinit var basicQuestionView: TextView
    private lateinit var imageView: ImageView
    private lateinit var answersView: RecyclerView
    private lateinit var nextButton: Button
    private lateinit var scoreButton: Button
    private lateinit var randomButton: Button

    private var gson = Gson()

    val urlJSON = "http://192.168.56.1:8080/questions";
    var urlIMAGE = "http://192.168.56.1:8080/static/";

    val score = ScoreViewModel()
    var qtroller = QTroller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        basicQuestionView = findViewById(R.id.basic_question_view)
        imageView = findViewById(R.id.imageView)
        answersView = findViewById(R.id.listview)

        scoreButton = findViewById(R.id.scorebtn)
        randomButton = findViewById(R.id.randombtn)
        nextButton = findViewById(R.id.nextbtn)

        fun setImage(location: String) {
            val queue = Volley.newRequestQueue(this)
            val imageRequest = ImageRequest(
                urlIMAGE + location,
                { response: Bitmap ->
                    // Display the first 500 characters of the response string.
                    imageView.setImageBitmap(response)
                },
                0,0,
                ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565,

                { error ->  basicQuestionView.text = "Error: ${error}" })

            queue.add(imageRequest)
        }

        fun renderQuestionData() {
            basicQuestionView.text = qtroller.currentQuestion().getQuestion()
            setImage( qtroller.currentQuestion().getImageName() )

            answersView.adapter = ItemAdapter(qtroller.currentQuestion().getAnswers().getAnswerList(), score)
            answersView.layoutManager = LinearLayoutManager(baseContext)
        }

        fun getQuestions() {
            val queue = Volley.newRequestQueue(this)
            val jsonArrayRequest = JsonArrayRequest(Request.Method.GET,
                urlJSON,
                null,
                { response ->
                    // Display the first 500 characters of the response string

                    qtroller.getQuestions().addAll(gson.fromJson(response.toString(), Array<Question>::class.java ).toList())
                    renderQuestionData()

                    nextButton.setOnClickListener {
                        qtroller.linearNextQuestion()
                        renderQuestionData()
                    }

                    randomButton.setOnClickListener {
                        qtroller.linearNextQuestion()
                        renderQuestionData()
                    }
                },
                { error ->  basicQuestionView.text = "Error: ${error}" })

            queue.add(jsonArrayRequest)
        }

        getQuestions()
    }
}

