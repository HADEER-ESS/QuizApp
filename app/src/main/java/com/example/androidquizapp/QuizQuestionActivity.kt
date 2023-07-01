package com.example.androidquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.androidquizapp.databinding.ActivityQuizQuestionBinding
import com.google.gson.Gson
import java.io.InputStream
import java.lang.Exception


class QuizQuestionActivity : AppCompatActivity(){
    private var increment = 1;
    private var questionsLength :Int? = null
    private var questions : Array<QuestionSkeleton>? = null
    private var selectedOptionPosition : Int = 0
    var correctedAnswerCount: Int = 0;
    private var optionsArray = ArrayList<TextView>()

    private lateinit var binding: ActivityQuizQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //parse JSON using Gson method
        questions = Gson().fromJson(getJSONFromAssetsFolder() , Array<QuestionSkeleton>::class.java)

        questionsLength = questions?.size

        displayQuestionOnScreen(questions!![increment-1])

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

        if(selectedOptionPosition == 0){
            Log.e("Zero false" , "selected $selectedOptionPosition")
            binding.answerBtn.isClickable = false
        }
        else{
            binding.answerBtn.isClickable = true
            Log.e("Zero true " , "selected $selectedOptionPosition")
            binding.answerBtn.setOnClickListener {
                if(binding.answerBtn.text == "Next Question" && increment<questionsLength!!){
                    binding.answerBtn.text = "Submit"
                    selectedOptionPosition = 0
                    defaultOptionClicked()
                    ++increment
                    displayQuestionOnScreen(questions!![increment-1])
                }
                else if(binding.answerBtn.text == "Finish" && increment == questionsLength!!){
                    intent.putExtra("correctAnswers" , correctedAnswerCount)
                    startActivity(intent)
                }
            }
        }
    }

    private fun displayCorrectOptionStyle(txt : TextView){
        txt.background = ContextCompat.getDrawable(
            this, R.drawable.correct_answer_option
        )
//        btn.text = "Next Question"
    }

    //OnOptionClicked change the style of it
    private fun defaultOptionClicked() {

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

        var currentCorrectAnswerNum : Int? = questions?.get(increment-1)?.correctAnswer
        selectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface , Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this , R.drawable.selected_option_container)
        if(selectedOptionPosition == currentCorrectAnswerNum){
            tv.background = ContextCompat.getDrawable(this , R.drawable.correct_answer_option)
            correctedAnswerCount += 1
            binding.answerBtn.text = "Next Question"
            if(increment == questionsLength!!){
                binding.answerBtn.text = "Finish"
            }
        }else{
            tv.background = ContextCompat.getDrawable(this , R.drawable.wrond_answer_option)
            displayCorrectOptionStyle(optionsArray[currentCorrectAnswerNum?.minus(1)!!])
            binding.answerBtn.text = "Next Question"
            if(increment == questionsLength!!){
                binding.answerBtn.text = "Finish"
            }
        }
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

}