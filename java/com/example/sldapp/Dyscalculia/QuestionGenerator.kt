package com.example.sldapp.Dyscalculia

import android.util.Log
import android.widget.Toast
import com.example.sldapp.R


object QuestionGenerator {

    const val QUIZ_RESULT : String = "quiz_result"
    const val QUESTION_NUMBER : String = "question_number"

    fun getQuestions(questionNumber : Int) : ArrayList<Question> {
        val questionList = ArrayList<Question>()

        //generiranje 10 pitanja koristeći nasumične brojeve
        for(i in 1..questionNumber) {
            var randomFirstNumber = (1..10).random()
            var randomSecondNumber = (1..10).random()
            val randomOrder = (0..2).random()
            val randomOperator = (0..2).random()
            var randomFirstAnswer = (1..15).random()
            var randomSecondAnswer = (1..15).random()

            //ako je operator množenje, generiramo veće brojeve u odgovorima
            if(randomOperator == 2){
                randomFirstAnswer = (1..40).random()
                randomSecondAnswer = (1..40).random()
            }

            //provjera za uklanjanje negativnih rezultata ako je operacija oduzimanje (1)
            if(randomOperator == 1 && randomFirstNumber <= randomSecondNumber)
            {
                randomFirstNumber = randomSecondNumber
                if(randomFirstNumber == 1){
                    randomSecondNumber = 0
                }
                else{
                    randomSecondNumber = (1 until randomFirstNumber-1).random()
                }
            }

            val randomQuestion = setRandomQuestion(randomFirstNumber, randomSecondNumber, randomOperator)
            val randomThirdAnswer = setThirdAnswer(randomFirstNumber,randomSecondNumber,randomOperator)

            //ponovo generiranje mogućih odgovora ako su neki od njih jednaki
            if(randomFirstAnswer == randomSecondAnswer ||randomFirstAnswer == randomThirdAnswer){
                randomFirstAnswer = (1..15).random()
            }else if(randomSecondAnswer == randomThirdAnswer){
                randomSecondAnswer = (1..15).random()
            }

            //generiranje tri različita redoslijeda odgovora, randomThirdAnswer je točan odgovor
            when(randomOrder){
                0 ->{
                    val question = Question(
                    i,
                    randomQuestion,
                    "$randomFirstAnswer",
                    "$randomSecondAnswer",
                    "$randomThirdAnswer",
                    setDrawable(randomFirstNumber),
                    setDrawable(randomSecondNumber),
                    3
                )
                    questionList.add(question)
                }
                 1 -> {
                     val question = Question(
                         i,
                         randomQuestion,
                         "$randomThirdAnswer",
                         "$randomSecondAnswer",
                         "$randomFirstAnswer",
                         setDrawable(randomFirstNumber),
                         setDrawable(randomSecondNumber),
                         1
                     )
                     questionList.add(question)

                 }
                2 -> {
                    val question = Question(
                        i,
                        randomQuestion,
                        "$randomSecondAnswer",
                        "$randomThirdAnswer",
                        "$randomFirstAnswer",
                        setDrawable(randomFirstNumber),
                        setDrawable(randomSecondNumber),
                        2
                    )
                    questionList.add(question)
                }
            }
        }
        return questionList
    }
}

//funkcija koja generira sliku za svaki od brojeva
fun setDrawable(randomFirstNumber : Int) : Int {
    return when (randomFirstNumber){
        1 -> {
            R.drawable.dyscalculia_apple_one
        }
        2 -> {
            R.drawable.dyscalculia_apple_two
        }
        3 -> {
            R.drawable.dyscalculia_apple_three
        }
        4 -> {
            R.drawable.dyscalculia_apple_four
        }
        5 -> {
            R.drawable.dyscalculia_apple_five
        }
        6 -> {
            R.drawable.dyscalculia_apple_six
        }
        7 -> {
            R.drawable.dyscalculia_apple_seven
        }
        8 -> {
            R.drawable.dyscalculia_apple_eight
        }
        9 -> {
            R.drawable.dyscalculia_apple_nine
        }
        else -> {
            R.drawable.dyscalculia_apple_ten
        }
    }
}

//funkcija koja generira točan odgovor
fun setThirdAnswer (randomFirstNumber: Int, randomSecondNumber : Int, randomOperator : Int) : Int {
    return when(randomOperator){
        0 -> {
            randomFirstNumber + randomSecondNumber
        }
        1 -> {
            randomFirstNumber - randomSecondNumber
        }
        else -> {
            randomFirstNumber * randomSecondNumber
        }
    }
}

//funkcija koja slučajno generira operaciju i vraća string pitanja
fun setRandomQuestion(randomFirstNumber: Int, randomSecondNumber: Int, randomOperator: Int) : String {
    return when(randomOperator){
        0 -> {
            "$randomFirstNumber + $randomSecondNumber =?"
        }
        1 -> {
            "$randomFirstNumber - $randomSecondNumber =?"
        }
        else -> {
            "$randomFirstNumber * $randomSecondNumber =?"
        }
    }
}
