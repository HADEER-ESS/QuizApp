package com.example.androidquizapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.androidquizapp.databinding.ActivityMainBinding
import com.example.androidquizapp.databinding.ActivityQuizQuestionBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import java.io.File
import java.io.InputStream
import java.lang.Exception
import java.nio.charset.Charset

class QuizQuestionActivity : AppCompatActivity() {
    var renderedData :ArrayList<JSONObject> = ArrayList()

    private lateinit var binding: ActivityQuizQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var questions = Gson().fromJson(getJSONFromAssetsFolder() , Array<QuestionSkeleton>::class.java)

        val questionsLength :Int = questions.size

        var increment = 0
        binding.answerBtn.setOnClickListener {
            ++increment
            binding.quizQuestionTv.text = questions[increment].question
            binding.countryImageIv.setImageResource(resources.getIdentifier(questions[increment].image , "drawable" , "com.example.androidquizapp"))
            binding.optionOneTv.text = questions[increment].optionOne
            binding.optionTwoTv.text = questions[increment].optionTwo
            binding.optionThreeTv.text = questions[increment].optionThree
            binding.optionFourTv.text = questions[increment].optionFour
        }
        binding.quizQuestionTv.text = questions[increment].question
//        val img = questions[increment].image
        val image = resources.getIdentifier(questions[increment].image ,
            "drawable" , "com.example.androidquizapp")
        Log.i("Image" , "int $image")
        Log.i("Image222" , "int ${R.drawable.ic_egypt}")
        binding.countryImageIv.setImageResource(image)
        binding.optionOneTv.text = questions[increment].optionOne
        binding.optionTwoTv.text = questions[increment].optionTwo
        binding.optionThreeTv.text = questions[increment].optionThree
        binding.optionFourTv.text = questions[increment].optionFour


//        try {
//            val obj = JSONObject(loadJSONFromAsset())
//            val questions = obj.getJSONArray("questions")
//
//            for(i in 0 until questions.length()){
//                val questionInfo = questions.getJSONObject(i)
//                renderedData.add(questionInfo)
////                println("QUESTION... $questionInfo")
//            }
//        }catch (ex :Exception){
//            ex.printStackTrace()
//        }
    }

    //Load JSON
//    private fun loadJSONFromAsset():String{
//        var json :String? = null
//
//        try {
//            val inputStream :InputStream = assets.open("questions.json")
//            val sizeData = inputStream.available()
//            val buffer = ByteArray(sizeData)
//            val charset :Charset = Charsets.UTF_8
//            inputStream.read(buffer)
//            inputStream.close()
//            json = String(buffer , charset)
//        }catch (ex : Exception){
//            ex.printStackTrace()
//        }
//        return json!!
//    }


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

    //Parse JSON using Gson
//    fun parseJSON() {
//        Gson().fromJson(getJSONFromAssetsFolder(), QuestionSkeleton::class.java)
//    }
}