package com.example.storeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val username = findViewById<EditText>(R.id.User_name).text.toString()
        val pass = findViewById<EditText>(R.id.User_password).text.toString()
        val SignUpBtn = findViewById<Button>(R.id.sign_up)
        val LogInBtn = findViewById<Button>(R.id.log_in)
        val db = DBHelper(this, null)
        LogInBtn.setOnClickListener {

//try to use key
          //val res : Boolean=  db.CheckUser(username,pass)
            //Toast.makeText(this,  " res " + res.toString(), Toast.LENGTH_LONG).show()
            /*if (res) {
                Toast.makeText(this,   " log in success", Toast.LENGTH_LONG).show()
              //
            }else  Toast.makeText(this,   " log in ERROR", Toast.LENGTH_LONG).show()*/
            intent = Intent(this , user_page::class.java)
            startActivity(intent)
        }

        SignUpBtn.setOnClickListener {
            intent =Intent(this, sign_up::class.java)
             startActivity(intent)
        }

    }
}