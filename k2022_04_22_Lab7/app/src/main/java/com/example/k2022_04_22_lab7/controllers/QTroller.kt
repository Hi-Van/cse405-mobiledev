package com.example.k2022_04_22_lab7.controllers

import java.util.*
import com.example.k2022_04_22_lab7.models.questions.AllQuestions
import com.example.k2022_04_22_lab7.models.questions.Question

class QTroller {

    private var bank: MutableList<Question> = mutableListOf<Question>()
    private var question: Int = 0

    fun getQuestions(): MutableList<Question> {
        return bank
    }

    fun numQuestions():Int {
        return bank.size
    }

    fun currentQuestion(): Question {
        return bank[question]
    }

    fun linearNextQuestion() {
        question = (question+1) % bank.size
    }

    fun pseudoRandomNextQuestion() {
        question = ( 0 until bank.size).random()
    }


}