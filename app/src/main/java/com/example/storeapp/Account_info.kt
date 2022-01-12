package com.example.storeapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Account_info : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_account_info)

        val EditBtn=findViewById<Button>(R.id.editInfoBtn)
        val DeleteBtn = findViewById<Button>(R.id.deleteBtn)
        val user:String=intent.getStringExtra("UserName").toString()
        val db = DBHelper(this, null)
        val name = findViewById<TextView>(R.id.name)
        val last_name = findViewById<TextView>(R.id.last_name)
        val number = findViewById<TextView>(R.id.Phonenumber)
        val addressr = findViewById<TextView>(R.id.address)
        val pass = findViewById<TextView>(R.id.password)
        findViewById<TextView>(R.id.user_info).text="Welcome "+name + " "+ last_name

        val cursor = db.getUserDataEdit(user)

        cursor!!.moveToFirst()
        var uses : String = cursor.getString(cursor.getColumnIndex(DBHelper.User_Name.toString()))
        if (user==uses){
            name.text=cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl.toString()))
            last_name.text=cursor.getString(cursor.getColumnIndex(DBHelper.Last_name.toString()))
            number.text=cursor.getString(cursor.getColumnIndex(DBHelper.Phone_col.toString()))
            addressr.text=cursor.getString(cursor.getColumnIndex(DBHelper.USER_ADDRESS.toString()))
            pass.text=cursor.getString(cursor.getColumnIndex(DBHelper.User_password.toString()))

        }
        while (cursor.moveToNext()){
            uses = cursor.getString(cursor.getColumnIndex(DBHelper.User_Name.toString())).toString()
            if (user==uses){
                name.text=cursor.getString(cursor.getColumnIndex(DBHelper.NAME_COl.toString()))
                last_name.text=cursor.getString(cursor.getColumnIndex(DBHelper.Last_name.toString()))
                number.text=cursor.getString(cursor.getColumnIndex(DBHelper.Phone_col.toString()))
                addressr.text=cursor.getString(cursor.getColumnIndex(DBHelper.USER_ADDRESS.toString()))
                pass.text=cursor.getString(cursor.getColumnIndex(DBHelper.User_password.toString()))



                break
            }

        }
        EditBtn.setOnClickListener(){
            val name = findViewById<EditText>(R.id.name).text.toString()
            val last_name = findViewById<EditText>(R.id.last_name).text.toString()
            val number:Int=findViewById<EditText>(R.id.Phonenumber).text.toString().toInt()
            val addressr = findViewById<EditText>(R.id.address).text.toString()
            val pass = findViewById<EditText>(R.id.password).text.toString()
            val user_name = findViewById<EditText>(R.id.UserName).text.toString()

            db.updateUsser(name,last_name,pass,addressr,user_name,number)


            Toast.makeText(this, name + " UPDATED", Toast.LENGTH_LONG).show()
            intent = Intent(this, Account_info::class.java)
            intent.putExtra("UserName", user)
            startActivity(intent)
        }
        DeleteBtn.setOnClickListener(){
            val x:Int= db.deleteUser(user)
            Toast.makeText(this,"Deleted " + x +""+ user,Toast.LENGTH_LONG).show()
            intent=Intent(this,MainActivity::class.java)
            //intent.putExtra("d",true )
            startActivity(intent)

        }



    }
}