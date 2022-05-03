package com.example.k2022_03_08_rv.controller

import android.media.AudioAttributes
import com.example.k2022_03_08_rv.model.RadioStations
import android.media.MediaPlayer

class RadioController {
    private var mp: MediaPlayer = MediaPlayer()
    private var stations: RadioStations = RadioStations()
    private var idx = 1
    private var playing: Boolean = false

    fun setUp() {
        val url = stations.getStations()[idx].uri

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
        mp.prepareAsync()
    }

    fun sources(): RadioStations {
        return stations
    }

    fun play() {
        mp.start()
    }

    fun pause() {
        mp.pause()
    }

    fun stop() {
        mp.stop()
    }

    fun toggle() {
        playing != playing
    }

    fun isPlaying(): Boolean {
        return playing
    }

    fun next() {
        idx = (idx + 1) % (stations.size() + 1)

        if (idx == 0) {
            idx += 1
        }

    }

    fun prev() {
        idx -= 1

        if (idx == 0) {
            idx = stations.size()
        }

    }

    fun set(i: Int) {
        idx = i
    }
}