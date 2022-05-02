package com.example.k2022_04_22_lab7.models.questions

class AnswerList(lst: List<AnswerObject>) {
     private var answerList = mutableListOf<AnswerObject>()

    fun numberOfAnswers() : Int {
        return answerList.size
    }

    fun setAnswerList(aList: List<AnswerObject>) {
        answerList = aList.toMutableList()
    }

    fun addAnswer(answerString: String, isTrue: String) {
        answerList.add(AnswerObject(answerString,isTrue))
    }
}