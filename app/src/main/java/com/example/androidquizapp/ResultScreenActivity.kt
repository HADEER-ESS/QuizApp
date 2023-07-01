package com.example.androidquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.androidquizapp.databinding.ActivityResultScreenBinding
import kotlin.system.exitProcess

class ResultScreenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityResultScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = getIntent()

        val correctAnswersData: Int = intent.getIntExtra("correctAnswers" , 1)
        val numOfQuestions : Int = intent.getIntExtra("QuestionsNum" , 1)

        Log.e("Answers " , "Correct Answers are $correctAnswersData")
        binding.tvScoreCount.text = "$correctAnswersData / $numOfQuestions"
        when(correctAnswersData){
            in 0..5 -> {
                binding.tvWinState.text = "You Lose"
                binding.btnFinishButton.text = "Try Again"
                binding.btnFinishButton.setOnClickListener {
                    finish()
                    exitProcess(0)
                }
            }
            else -> {
                binding.btnFinishButton.text = "Exit"
                binding.btnFinishButton.setOnClickListener {
                    finishAffinity()
                }
            }
        }
    }
}

