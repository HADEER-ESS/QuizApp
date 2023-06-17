package com.example.androidquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.json.JSONObject
import java.io.InputStream
import java.lang.Exception
import java.nio.charset.Charset

class QuizQuestionActivity : AppCompatActivity() {
    var renderedData :ArrayList<JSONObject> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        try {
            val obj = JSONObject(loadJSONFromAsset())
            val questions = obj.getJSONArray("questions")

            for(i in 0 until questions.length()){
                val questionInfo = questions.getJSONObject(i)
                renderedData.add(questionInfo)
//                println("QUESTION... $questionInfo")
            }
        }catch (ex :Exception){
            ex.printStackTrace()
        }
    }

    //Load JSON
    private fun loadJSONFromAsset():String{
        var json :String? = null

        try {
            val inputStream :InputStream = assets.open("questions.json")
            val sizeData = inputStream.available()
            val buffer = ByteArray(sizeData)
            val charset :Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer , charset)
        }catch (ex : Exception){
            ex.printStackTrace()
        }
        return json!!
    }


    //Read JSON as STRING from Asset Folder
//    private fun getJSONFromAssetsFolder ():String{
//        var json : String? = null
//
//        try {
//            val inputStream:InputStream = assets.open("questions.json")
//            json = inputStream?.bufferedReader().use { it?.readText() }
//            println("The Json File Data Length ${json?.length}")
//            Log.i("Data " , json!!)
//        }catch (err : Exception){
//            err.printStackTrace()
//        }
//        return json!!
//    }

    //Parse JSON using Gson
//    fun parseJSON() {
//        val item = Gson().fromJson("questions.json" , QuestionSkeleton::class.java)
//        println("ITEEEMM $item")
//        Log.i(
//            "Parse ",
//            "${Gson().fromJson(getJSONFromAssetsFolder(), QuestionSkeleton::class.java)}"
//        )
//        Gson().fromJson(getJSONFromAssetsFolder(), QuestionSkeleton::class.java)
//    }
}