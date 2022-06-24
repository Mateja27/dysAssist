package com.example.sldapp.Dyscalculia

data class Question(
    val id : Int,
    val question : String,
    val  optionOne : String,
    val optionTwo : String,
    val optionThree : String,
    val leftApple : Int,
    val rightApple : Int,
    val correctOption : Int
)
