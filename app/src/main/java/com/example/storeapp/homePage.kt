package com.example.storeapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class homePage : AppCompatActivity() {
    private var itemsList = mutableListOf<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            val items =
                getSharedPreferences("CartItems", Context.MODE_PRIVATE).getString("name", "noitems")
            val qtt =
                getSharedPreferences("CartItems", Context.MODE_PRIVATE).getInt("selectedOption", 0)
            itemsList.add("hp 305 Quntity :" + qtt + " name" + items)
            intent = Intent(this, ShoppingCart::class.java)
            startActivity(intent)
            Toast.makeText(this, "clear", Toast.LENGTH_LONG).show()
        }


    }

    fun onClick(v: View) {

        var dialog = CustomDialogFragment(""+v.resources.getResourceEntryName(v.id))
        dialog.show(supportFragmentManager, "Shop to your cart ")

    }

}