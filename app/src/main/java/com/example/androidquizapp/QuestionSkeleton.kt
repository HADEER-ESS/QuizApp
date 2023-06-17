package com.example.androidquizapp

data class QuestionSkeleton(
    val id : Int,
    val question : String,
    val image : String,
    val optionOne : String,
    val optionTwo : String,
    val optionThree : String,
    val optionFour : String,
    val correctAnswer : Int
)
