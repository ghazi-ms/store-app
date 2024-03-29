package com.example.storeapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class homePage : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_home_page)

        val user: String = intent.getStringExtra("UserName").toString()
        val usertxt = findViewById<TextView>(R.id.name_TXT)
        usertxt.text = "Welcome " + user
        usertxt.setOnClickListener {
            intent = Intent(this, Account_info::class.java)
            intent.putExtra("UserName", user)
            startActivity(intent)

        }
        val t = findViewById<TextView>(R.id.ViewCart)
        t.setOnClickListener {

            intent = Intent(this, ShoppingCart::class.java)
            startActivity(intent)

        }


    }

    fun onClick(v: View) {

        var dialog = CustomDialogFragment(""+v.resources.getResourceEntryName(v.id))
        dialog.show(supportFragmentManager, "Shop to your cart ")

    }

}