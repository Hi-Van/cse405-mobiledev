package com.example.lab2.model

class ScoreTracker {

    private var score: Int = 0

    fun inc() {
        score += 5
    }

    fun dec() {
        score -= 1

        if (score < 0) {
            score = 0
        }
    }

    fun reset() {
        score = 0
    }

    fun score(): Int {
        return score
    }
}