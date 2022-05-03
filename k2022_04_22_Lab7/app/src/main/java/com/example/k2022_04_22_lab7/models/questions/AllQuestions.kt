package com.example.k2022_04_22_lab7.models.questions

class AllQuestions(val allQuestions: MutableList<Question> = mutableListOf<Question>()) {

    fun length():Int {
        return allQuestions.size
    }

}