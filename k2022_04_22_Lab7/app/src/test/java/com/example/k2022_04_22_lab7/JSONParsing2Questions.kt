package com.example.k2022_04_22_lab7

import com.example.k2022_04_22_lab7.models.questions.Question
import com.example.k2022_04_22_lab7.models.questions.AnswerList
import com.example.k2022_04_22_lab7.models.questions.AnswerObject
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class JSONParsing2Questions {

    @Test
    fun basic_gson_object() {

        var gson: Gson = Gson()

        var tinyString: String = "{ first :1,  second: 2 }"

        class TinyC {
            var  first: Int = 0
            var  second: Int = 0
            fun getSum(): Int {
                return first+ second
            }
        }

        var tinyC: TinyC = gson.fromJson(tinyString, TinyC::class.java)

        assertEquals(3, tinyC.getSum())
    }

    @Test
    fun basic_gson_object_quotes() {

        var gson: Gson = Gson()

        var tinyString: String = "{ 'first' :1,  'second': 2 }"

        class TinyC {
            var  first: Int = 0
            var  second: Int = 0
            fun getSum(): Int {
                return first+ second
            }
        }

        var tinyC: TinyC = gson.fromJson(tinyString, TinyC::class.java)
        assertEquals(3, tinyC.getSum())
    }

    @Test
    fun basic_gson_array() {

        var gson: Gson = Gson()

        var tinyStringArray: String = "[ { first :1 , second: 2 }, { first :5 , second: 7 } ]"
        var sums: List<Int> = listOf(3,12)

        class TinyC {
            var  first: Int = 0
            var  second: Int = 0
            fun getSum(): Int {
                return first + second
            }
        }

        var tinyC_array: List<TinyC> = gson.fromJson(tinyStringArray, Array<TinyC>::class.java).toList()

        var i: Int = 0
        for (tinyC in tinyC_array) {
            assertEquals(tinyC.getSum(), sums[i])
            i += 1
        }
    }

    @Test
    fun question_gson_object() {

        var gson: Gson = Gson()

        var one_question_part1: String = "{ 'question': 'How many planets in our solar system', 'imageName' : 'stamford_harbor.jpg', 'answers' : { 'answerList' : [  {  'answer': 'there are 8 planets' , 'isTrue': 'true' }, {  'answer': 'there are 128 planets' , 'isTrue': 'false' } ] } }"

        var question: Question = gson.fromJson(one_question_part1,Question::class.java)

        assertEquals(2, question.numberOfAnswers())
    }

    @Test
    fun question_gson_list() {

        var gson: Gson = Gson()
        var array_answers: String =
            "[ { 'answer': 'good' }," +
                    "{ 'answer': 'bad' }" +
                    "]"

        var answerList: List<AnswerObject> = gson.fromJson(array_answers, Array<AnswerObject>::class.java ).toList()

        assertEquals(2, answerList.size)
    }

    @Test
    fun question_gson_list2() {

        var gson: Gson = Gson()
        var array_answers: String =
            "[ {  'answer': 'there are 8 planets' , 'isTrue': 'true' },  {  'answer': 'there are 128 planets' , 'isTrue': 'false' } ] "

        var answerList: List<AnswerObject> = gson.fromJson(array_answers, Array<AnswerObject>::class.java ).toList()

        assertEquals(2, answerList.size)
    }

    @Test
    fun answerList_gson_list() {

        var gson: Gson = Gson()
        var array_answers: String =
            "{ 'answerList' : [  {  'answer': 'there are 8 planets' , 'isTrue': 'true' }, {  'answer': 'there are 128 planets' , 'isTrue': 'false' } ] }"


        var aList: AnswerList = gson.fromJson(array_answers, AnswerList::class.java )
        assertEquals(2, aList.numberOfAnswers())
    }

    @Test
    fun question_gson() {

        var gson: Gson = Gson()
        var full_question: String =
            "{ 'question': 'How many planets', 'imageName': 'stamford_harbor.jpg', 'answers' : { 'answerList' : [  {  'answer': 'there are 8 planets' , 'isTrue': 'true' }, {  'answer': 'there are 128 planets' , 'isTrue': 'false' } ] } }"

        //  "{ 'question': 'How many planets', 'imageName': 'stamford_harbor.jpg', 'answerList' : [  {  'answer': 'there are 8 planets' , 'isTrue': 'true' }, {  'answer': 'there are 128 planets' , 'isTrue': 'false' } ] }"

        var question: Question = gson.fromJson(full_question, Question::class.java )
        assertEquals(2, question.numberOfAnswers())
    }


    @Test
    fun question_list_gson() {

        var gson: Gson = Gson()
        var full_question_list: String =
            "[ { 'question': 'How many planets', 'imageName': 'stamford_harbor.jpg', 'answers' : { 'answerList' : [  {  'answer': 'there are 8 planets' , 'isTrue': 'true' }, {  'answer': 'there are 128 planets' , 'isTrue': 'false' } ] } }," +
                    "{ 'question': 'How many seas', 'imageName': 'stamford_harbor.jpg', 'answers' : { 'answerList' : [  {  'answer': 'there are 8 seas' , 'isTrue': 'true' }, {  'answer': 'there are 7 seas' , 'isTrue': 'false' } ] } } ]"

        var questionList: List<Question> = gson.fromJson(full_question_list, Array<Question>::class.java ).toList()

        assertEquals("How many planets", questionList[0].getQuestion())
        assertEquals("How many seas", questionList[1].getQuestion())
        assertNotEquals("Good day?", questionList[1].getQuestion())
    }

}