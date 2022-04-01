package com.example.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONObject
import kotlin.math.log

class MainActivity : AppCompatActivity() {

    //initialize priovate widgets into callable variables
    private lateinit var scoreEditText: EditText
    private lateinit var btnRequest: Button
    private lateinit var btnReset: Button
    private lateinit var requestImage: ImageView
    private lateinit var scoreViewModel: ScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set variables to layout widgets
        scoreEditText = findViewById(R.id.scoreEditView)
        btnRequest = findViewById(R.id.btnreq)
        btnReset = findViewById(R.id.btnreset)
        requestImage = findViewById(R.id.reqimage)
        scoreViewModel = ViewModelProvider(this).get(ScoreViewModel::class.java)

        //observe latest changes in live data
        scoreViewModel.lastQuery().observe(this) {
            var str = it.toString()
            scoreEditText.setText(str)
        }

        //send REST request and update image
        btnRequest.setOnClickListener {

            scoreViewModel.getRequest()


            val queue = Volley.newRequestQueue(this)
            val url = scoreViewModel.lastQuery().value

            // Request a string response from the provided URL.
            val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->
                    // Display the response string.
                    Log.i("testingRequesting", "Response is: $response")

                    val idx = response.indexOf("primaryImageSmall")

                    val start = response.indexOf('"', idx + 18)
                    val end = response.indexOf('"', start + 1)

                    val imagerequested = response.substring(start + 1, end)
                    Log.i("testingImageURL", imagerequested)

                    Picasso
                        .get()
                        .load(imagerequested)
                        .into(requestImage)
                },
                { Log.i("testingRequesting", "Request Error!") })

            // Add the request to the RequestQueue.
            queue.add(stringRequest)
        }

        //reset image and hide request
        btnReset.setOnClickListener {
            requestImage.setImageResource(0)
            scoreViewModel.reset()
        }
    }
}