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
import com.example.k2022_03_08_rv.model.RadioStation
import com.example.k2022_03_08_rv.model.RadioStations

//const val url = "http://176.31.243.35:8014/stream"

class MainActivity : AppCompatActivity() {

    private var radioOn: Boolean = false
    private lateinit var radioButton : Button
    private lateinit var stationName: TextView
    private  lateinit var mediaPlayer: MediaPlayer
    private lateinit var recyclerView: RecyclerView
    private lateinit var nextButton: Button
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var radioStations = RadioStations()

        radioButton = findViewById(R.id.on_off_button)
        nextButton = findViewById(R.id.next)
        backButton = findViewById(R.id.back)
        stationName = findViewById(R.id.stationname)

        var stations: MutableList<RadioStation> = radioStations.getStations()
        var idx: Int = 0

        var url = stations[idx].uri.toString()
        setUpRadio(url)

        radioButton.setOnClickListener{

            if (radioOn) {
                mediaPlayer.pause()
                radioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.play,0,0,0)
            } else {
                mediaPlayer.start()
                radioButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.pause,0,0,0)
            }

            radioOn = ! radioOn
        }

        recyclerView = findViewById(R.id.recycleview)
        recyclerView.adapter = RadioAdapter(radioStations)
        recyclerView.layoutManager = LinearLayoutManager(baseContext)

    }

    private fun setUpRadio(url: String) {
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }
        setAndPrepareRadioLink(url)
    }

    private fun setAndPrepareRadioLink(url: String) {
        mediaPlayer.reset()
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
    }
}