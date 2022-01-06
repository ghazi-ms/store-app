package com.example.storeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val SignUpBtn = findViewById<Button>(R.id.sign_up)
        val LogInBtn = findViewById<Button>(R.id.log_in)


        SignUpBtn.setOnClickListener {
            intent =Intent(this, sign_up::class.java)
            startActivity(intent)
        }
    }
}