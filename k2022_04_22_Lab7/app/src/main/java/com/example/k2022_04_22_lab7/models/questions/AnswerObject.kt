package com.example.k2022_04_22_lab7.models.questions

class AnswerObject(var answerString: String, var isAnswerTrue: String) {

    private var answer = ""
    private var isTrue = ""

    fun setAnswer(aString: String) {
        answer = aString
    }
    fun getAnswer() : String {
        return answer
    }

    fun setIsTrue(bValue: String) {
        isTrue = bValue
    }
    fun getIsTrue(): String {
        return isTrue
    }

}