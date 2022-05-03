package com.example.k2022_03_08_rv

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.k2022_03_08_rv.controller.RadioController
import com.example.k2022_03_08_rv.model.RadioStation
import com.example.k2022_03_08_rv.model.RadioStations

//const val url = "http://176.31.243.35:8014/stream"

class MainActivity : AppCompatActivity() {

    private lateinit var radioButton : Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var rc: RadioController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rc = RadioController()

        radioButton = findViewById(R.id.on_off_button)
        rc.setUp()

        radioButton.setOnClickListener{

            if (rc.isPlaying()) {
                rc.pause()
                radioButton.setText("Radio On")
            } else {
                rc.play()
                radioButton.setText("Radio Off")
            }

            rc.toggle()
        }

        recyclerView = findViewById(R.id.recycleview)
        recyclerView.adapter = RadioAdapter( rc )
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
    }
}