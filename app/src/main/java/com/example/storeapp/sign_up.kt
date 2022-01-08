package com.example.storeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class sign_up : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val addrecord = findViewById<Button>(R.id.DoneBtn)
        addrecord.setOnClickListener {
            val db = DBHelper(this, null)

            // creating variables for values
            // in name and age edit texts
            val name = findViewById<EditText>(R.id.name).text.toString()
            val last_name = findViewById<EditText>(R.id.last_name).text.toString()
            val number = findViewById<EditText>(R.id.Phonenumber).text.toString().toInt()
            val addressr = findViewById<EditText>(R.id.address).text.toString()
            val pass = findViewById<EditText>(R.id.password).text.toString()
            val user_name = findViewById<EditText>(R.id.UserName).text.toString()
            // calling method to add
            // name to our database
            db.adduser(number,name,last_name,pass,addressr,user_name)

            // Toast to message on the screen
            Toast.makeText(this, name + " added to database", Toast.LENGTH_LONG).show()

            // at last, clearing edit texts
            findViewById<EditText>(R.id.name).text.clear()
            findViewById<EditText>(R.id.last_name).text.clear()
            findViewById<EditText>(R.id.Phonenumber).text.clear()
            findViewById<EditText>(R.id.address).text.clear()
            findViewById<EditText>(R.id.password).text.clear()
            findViewById<EditText>(R.id.UserName).text.clear()

            intent = Intent(this , MainActivity::class.java)
            startActivity(intent)
        }
    }
}