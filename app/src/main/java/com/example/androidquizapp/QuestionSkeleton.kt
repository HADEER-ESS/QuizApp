package com.example.androidquizapp

import android.graphics.Bitmap
import android.graphics.drawable.Drawable

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
