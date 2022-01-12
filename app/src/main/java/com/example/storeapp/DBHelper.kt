package com.example.storeapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.widget.*


class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {


        val query = ("CREATE TABLE "+ TABLE_NAME+" ("
                + Phone_col + " INTEGER, " +
                NAME_COl + " TEXT," +
                Last_name + " TEXT," +
                User_password + " TEXT," +
                USER_ADDRESS + " TEXT," +
                User_Name + " TEXT " +
                ");"
                )

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)

    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
       /* db.execSQL("DROP TABLE IF EXISTS ${TABLE_NAME}")
        onCreate(db)*/
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        //onUpgrade(db, oldVersion, newVersion)
    }



    fun getName(): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }
    // This method is for adding data in our database
    fun adduser(phone : Int , name : String , lastname : String , password : String , address : String , Username : String){
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(Phone_col, phone)
            put(NAME_COl, name)
            put(Last_name, lastname)
            put(User_password, password)
            put(USER_ADDRESS, address)
            put(User_Name, Username)
        }

        val newRowId = db?.insert(TABLE_NAME , null, values)



    }
    fun updateUsser( name : String , lastname : String , password : String , address : String , Username : String , number:Int){
        val db = this.writableDatabase
       db.execSQL("UPDATE "+ TABLE_NAME + " SET phone = "+number + ", first_name = '"+name+"', last_name='"+lastname+"', User_password = '"+password+"', User_address = '"+
       address+"', "+ "User_name = '"+Username+"' WHERE User_name = '"+Username+"' ")


    }
    fun getUserData(username : String ): Cursor? {

        // here we are creating a readable
        // variable of our database
        // as we want to read value from it
        val db = this.readableDatabase

        // below code returns a cursor to
        // read data from the database
        return db.rawQuery("SELECT User_name,User_password FROM " + TABLE_NAME, null)

    }
    fun drop(){
        val db= this.writableDatabase

        db.execSQL("DROP TABLE table_name")
    }
    fun getUserDataEdit(username : String ): Cursor? {


        val db = this.readableDatabase


        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }
    @Throws(SQLiteConstraintException::class)
    fun deleteUser(user_name:String) : Int {
        val db=this.writableDatabase

               db.execSQL("DELETE FROM " + TABLE_NAME+" WHERE User_name = '" +user_name+"'" )
      return 1;

    }
    companion object{
        // here we have defined variables for our database
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "users"

        // below is the variable for table name
        val TABLE_NAME = "data "

        // below is the variable for id column
        val Phone_col = "phone"

        // below is the variable for name column
        val NAME_COl = "first_name"

        // below is the variable for age column
        val Last_name = "last_name"

        val User_password ="User_password"

        val USER_ADDRESS = "User_address"
        val User_Name = "User_name"
    }
}