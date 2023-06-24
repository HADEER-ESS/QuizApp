package com.example.androidquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.androidquizapp.databinding.ActivityQuizQuestionBinding
import com.google.gson.Gson
import java.io.InputStream
import java.lang.Exception

class QuizQuestionActivity : AppCompatActivity() , View.OnClickListener{
    private var increment = 1;
    private var questionsLength :Int? = null
    private var questions : Array<QuestionSkeleton>? = null
    private var selectedOptionPosition : Int = 0
    var correctedAnswerCount: Int = 0;

    private lateinit var binding: ActivityQuizQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //parse JSON using Gson method
        questions = Gson().fromJson(getJSONFromAssetsFolder() , Array<QuestionSkeleton>::class.java)

        questionsLength = questions?.size

        displayQuestionOnScreen(questions!![increment-1])
//        onOptionClicked()
        binding.optionOneTv.setOnClickListener {
            onSelectedOptionClicked((it as TextView), 1)
        }
        binding.optionTwoTv.setOnClickListener {
            onSelectedOptionClicked((it as TextView), 2)
        }
        binding.optionThreeTv.setOnClickListener {
            onSelectedOptionClicked((it as TextView), 3)
        }
        binding.optionFourTv.setOnClickListener {
            onSelectedOptionClicked((it as TextView), 4)
        }

    }

    private fun displayQuestionOnScreen(question:QuestionSkeleton) {
        val intent = Intent(this , ResultScreenActivity::class.java)
        binding.quizQuestionTv.text = question.question

        val image = resources.getIdentifier(
            question.image,
            "drawable", "com.example.androidquizapp"
        )
        binding.countryImageIv.setImageResource(image)
        binding.progressBarPb.progress = increment
        binding.progressCountTv.text = "$increment / ${binding.progressBarPb.max}"
        binding.optionOneTv.text = question.optionOne
        binding.optionTwoTv.text = question.optionTwo
        binding.optionThreeTv.text = question.optionThree
        binding.optionFourTv.text = question.optionFour

        binding.answerBtn.setOnClickListener {
            if(selectedOptionPosition == question.correctAnswer){
                correctedAnswerCount += 1
                Log.i("Correct" , "YESSSSS $correctedAnswerCount")
            }
            if(increment == questionsLength){
                binding.answerBtn.text = "FINISH"
                intent.putExtra("correctAnswers" , correctedAnswerCount)
                startActivity(intent)
            }else{
                ++increment
                displayQuestionOnScreen(questions!![increment-1])
                binding.answerBtn.text = "SUBMIT"
            }

        }
    }

//    private fun correctAnswer (tv: TextView){
//        tv.background = ContextCompat.getDrawable(
//            this ,
//            R.drawable.correct_answer_option
//        )
//    }

    //OnOptionClicked change the style of it
    private fun defaultOptionClicked() {
        var optionsArray= ArrayList<TextView>()

        binding.optionOneTv.let {
            optionsArray.add(0, it)
        }
        binding.optionTwoTv.let {
            optionsArray.add(1, it)
        }
        binding.optionThreeTv.let {
            optionsArray.add(2, it)
        }
        binding.optionFourTv.let {
            optionsArray.add(3, it)
        }

        for(option in optionsArray){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_container
            )
        }
    }
    private fun onSelectedOptionClicked (tv : TextView , selectedOptionNum : Int){
        defaultOptionClicked()

        selectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface , Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this , R.drawable.selected_option_container)
    }

    //Read JSON as STRING from Asset Folder
    private fun getJSONFromAssetsFolder ():String{
        var json : String? = null

        try {
            val inputStream:InputStream = assets.open("questions.json")
            json = inputStream?.bufferedReader().use { it?.readText() }
        }catch (err : Exception){
            err.printStackTrace()
        }
        return json!!
    }

    override fun onClick(view: View?) {
        TODO("Not yet implemented")
    }
}