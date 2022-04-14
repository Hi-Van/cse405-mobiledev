package com.example.lab4

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var nextButton: Button
    private lateinit var randButton: Button
    private lateinit var prevButton: Button

    private lateinit var artName: TextView
    private lateinit var depName: TextView
    private lateinit var artistName: TextView

    private lateinit var image: ImageView

    private val art = arrayOf(437549, 12600, 437854, 18354, 11227, 435621, 437394, 436440, 459093, 436579)
    private val url = "https://collectionapi.metmuseum.org/public/collection/v1/objects/"

    private var idx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextButton = findViewById(R.id.nextbutton)
        randButton = findViewById(R.id.randombutton)
        prevButton = findViewById(R.id.prevbutton)

        artName = findViewById(R.id.artname)
        depName = findViewById(R.id.depname)
        artistName = findViewById(R.id.artistname)

        image = findViewById(R.id.img)

        //it yells at me if not included
        @SuppressLint("SetTextI18n")

        fun getImg(imgUrl: String) {
            val q = Volley.newRequestQueue(this)
            val r = ImageRequest(imgUrl,
                {response ->
                    image.setImageBitmap(response)
                },
                0,0, ImageView.ScaleType.CENTER_INSIDE, null,
                {error ->
                    Log.i("getImg", "Failed to request image!")
                }
            )

            q.add(r)
        }

        fun getJSON() {
            val q = Volley.newRequestQueue(this)
            val r = JsonObjectRequest(Request.Method.GET, url + art[idx].toString(), null,
                { response ->
                    artName.text = "${response.get("title")}"
                    depName.text = "Department: ${response.get("department")}"
                    artistName.text = "Artist: ${response.get("artistDisplayName")}"

                    getImg( "${response.get("primaryImageSmall")}" )
                },
                { error ->
                    Log.i("getJSON", "Unable to make JSON request!")
                }
            )

            q.add(r)
        }

        randButton.setOnClickListener {
            idx = (0..9).random()
            getJSON()
        }

        nextButton.setOnClickListener {
            idx += 1

            if (idx > 9) {
                idx = 0
            }

            getJSON()
        }

        prevButton.setOnClickListener {
            idx -= 1

            if (idx < 0) {
                idx = 9
            }

            getJSON()
        }

    }
}