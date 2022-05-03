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
import com.example.k2022_04_22_lab7.models.questions.AnswerObject
import com.example.k2022_04_22_lab7.models.questions.Question
import com.example.k2022_04_22_lab7.models.score.ScoreViewModel
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    private lateinit var basicQuestionView: TextView
    private lateinit var imageView: ImageView
    private lateinit var answersView: RecyclerView

    private var gson = Gson()
    private var questionList: List<Question>? = null;
    private var idx: Int = 0;

    val urlJSON = "http://192.168.56.1:8080/questions";
    var urlIMAGE = "http://192.168.56.1:8080/static/stamford_harbor.jpg";
    val score = ScoreViewModel()
    var test = mutableListOf<AnswerObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        basicQuestionView = findViewById(R.id.basic_question_view)
        imageView = findViewById(R.id.imageView)
        answersView = findViewById(R.id.listview)


        fun getQuestions() {
            val queue = Volley.newRequestQueue(this)
            val jsonArrayRequest = JsonArrayRequest(Request.Method.GET,
                urlJSON,
                null,
                { response ->
                    // Display the first 500 characters of the response string

                    questionList = gson.fromJson(response.toString(), Array<Question>::class.java ).toList()

                    test.addAll(questionList!![0].getAnswers().getAnswerList())
                },
                { error ->  basicQuestionView.text = "Error: ${error}" })

            queue.add(jsonArrayRequest)
        }

        fun setImage() {
            val queue = Volley.newRequestQueue(this)
            val imageRequest = ImageRequest(
                urlIMAGE,
                { response: Bitmap ->
                    // Display the first 500 characters of the response string.
                    imageView.setImageBitmap(response)
                },
                0,0,
                ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565,

                { error ->  basicQuestionView.text = "Error: ${error}" })

            queue.add(imageRequest)
        }

        getQuestions()

        answersView.adapter = ItemAdapter(test, score)
        answersView.layoutManager = LinearLayoutManager(baseContext)

    }
}

