package com.example.storeapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val SignUpBtn = findViewById<Button>(R.id.sign_up)
        val LogInBtn = findViewById<Button>(R.id.log_in)
        val db = DBHelper(this, null)

        LogInBtn.setOnClickListener {

            val username = findViewById<EditText>(R.id.User_name).text.toString()
            val pass = findViewById<EditText>(R.id.User_password).text.toString()
            val cursor = db.getUserData(username)
            cursor!!.moveToFirst()
            var uses : String = cursor.getString(cursor.getColumnIndex(DBHelper.User_Name.toString()))
            var pas : String =  cursor.getString(cursor.getColumnIndex(DBHelper.User_password.toString()))

            if (username == uses && pass == pas) {

               Toast.makeText(this, uses + " : " + pas, Toast.LENGTH_LONG).show()
                cursor.close()
                intent = Intent(this, homePage::class.java)
                intent.putExtra("UserName", username)
                startActivity(intent)
           }else {
                while (cursor.moveToNext()) {
                    uses = cursor.getString(cursor.getColumnIndex(DBHelper.User_Name.toString())).toString()
                    pas = cursor.getString(cursor.getColumnIndex(DBHelper.User_password.toString())).toString()

                   if (username == uses && pass == pas) {

                        Toast.makeText(this, uses + " : " + pas, Toast.LENGTH_LONG).show()
                        cursor.close()

                       intent = Intent(this, homePage::class.java)
                       intent.putExtra("UserName", username)
                        startActivity(intent)
                       break
                    }

                }
            }

        }

        SignUpBtn.setOnClickListener {

          intent =Intent(this, sign_up::class.java)
             startActivity(intent)
        }

    }
}