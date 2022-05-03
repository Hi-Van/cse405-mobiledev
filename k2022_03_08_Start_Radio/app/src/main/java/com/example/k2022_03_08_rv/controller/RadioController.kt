package com.example.k2022_03_08_rv.controller

import android.media.AudioAttributes
import com.example.k2022_03_08_rv.model.RadioStations
import android.media.MediaPlayer

class RadioController {
    private var mp: MediaPlayer = MediaPlayer()

    fun setUp(url: String) {
        mp = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
        }

        mp.reset()
        mp.setDataSource(url)
        mp.prepare()
    }

    fun play() {
        mp.start()
    }

    fun pause() {
        mp.pause()
    }
}