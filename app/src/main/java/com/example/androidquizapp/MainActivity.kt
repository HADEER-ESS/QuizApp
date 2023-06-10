package com.example.androidquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var startBTN : Button? = null
    private var textInput : EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startBTN  = findViewById(R.id.Start_btn)
        textInput = findViewById(R.id.userEnterName)
    }
     fun invokeName(view : View){
        Toast.makeText(this , "Hello ${textInput?.text}" , Toast.LENGTH_LONG).show()
    }

}