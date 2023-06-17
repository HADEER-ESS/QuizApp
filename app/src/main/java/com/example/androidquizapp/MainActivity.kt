package com.example.androidquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.androidquizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.StartBtn?.setOnClickListener {
            if(binding.userEnterNameEt?.text?.isEmpty() == true){
                Toast.makeText(this , "Please Enter Your Name" , Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this , QuizQuestionActivity::class.java)
                startActivity(intent)
                //to prevent the back_button to navigate to the previous stack we add
                //we add a finish method, to quit the application when click on the back_button
                finish()
            }
        }
    }

}