package com.example.lab2.controller

import com.example.lab2.model.QBank

class QTroller {

    private var questBank: QBank = QBank()
    private var currentIdx: Int = 0

    private var length: Int = questBank.questions.size - 1

    public fun currQuestion(): Int {
        return questBank.questions[currentIdx].questionId
    }

    public fun currAnswer(): Boolean {
        return questBank.questions[currentIdx].answer
    }

    public fun nextQuestion(): Int {
        currentIdx += 1

        if (currentIdx > length) {
            currentIdx = 0
        }

        return questBank.questions[currentIdx].questionId
    }

    public fun randomQuestion(): Int {
        currentIdx = (0..length).random()

        return questBank.questions[currentIdx].questionId
    }
}