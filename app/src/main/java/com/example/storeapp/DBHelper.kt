package com.example.storeapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    // below is the method for creating a database by a sqlite query
    override fun onCreate(db: SQLiteDatabase) {
        // below is a sqlite query, where column names
        // along with their data types is given
       // val query = ("DROP TABLE " + TABLE_NAME )
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + Phone_col + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                Last_name + " TEXT," +
                User_password + " TEXT," +
                USER_ADDRESS + " TEXT, " +
                User_Name + " TEXT " +
                ")")

        // we are calling sqlite
        // method for executing our query
        db.execSQL(query)

    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        // this method is to check if table already exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
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


        // below we are creating
        // a content values variable
        val values = ContentValues()

        // we are inserting our values
        // in the form of key-value pair
        values.put(Phone_col, phone)
        values.put(NAME_COl, name)
        values.put(Last_name, lastname)
        values.put(User_password, password)
        values.put(USER_ADDRESS, address)
        values.put(User_Name, Username)
        // here we are creating a
        // writable variable of
        // our database as we want to
        // insert value in our database
        val db = this.writableDatabase

        // all values are inserted into database
        db.insert(TABLE_NAME, null, values)

        // at last we are
        // closing our database
       db.close()
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
    companion object{
        // here we have defined variables for our database

        // below is variable for database name
        private val DATABASE_NAME = "users"

        // below is the variable for database version
        private val DATABASE_VERSION = 1

        // below is the variable for table name
        val TABLE_NAME = "table_name"

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