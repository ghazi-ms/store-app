package com.example.storeapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class ShoppingCart : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter

    private var itemsList = mutableListOf<String>()
    private var displayList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_cart)
        try {


            val x = getSharedPreferences("CartItems", Context.MODE_PRIVATE).getStringSet(
                "list",
                null
            ) as HashSet<String>

            val t = x.iterator()
            for (element in t) {
                itemsList.add(element)

            }

            displayList.addAll(itemsList)
            recyclerView = findViewById(R.id.rex)
            recyclerAdapter = RecyclerAdapter(displayList)
            recyclerView.adapter = recyclerAdapter


            val clear = findViewById<Button>(R.id.clearBtn)
            clear.setOnClickListener {

                itemsList.clear()
                displayList.clear()
                x.clear()
                getSharedPreferences("CartItems", Context.MODE_PRIVATE).edit().clear().commit()
                recyclerAdapter.notifyDataSetChanged()
                finish()
                overridePendingTransition(0, 0)
                startActivity(intent)
                overridePendingTransition(0, 0)

            }
        } catch (exp: Exception) {
            Log.d(exp.toString(), "sssssssssssssssssssssssssssssssssssss")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        val item: MenuItem = menu!!.findItem(R.id.action_search)
        if (item != null) {
            val searchView = item.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {


                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    if (newText!!.isNotEmpty()) {
                        displayList.clear()
                        val search = newText.lowercase(Locale.getDefault())

                        for (items in itemsList) {
                            if (items.lowercase(Locale.getDefault()).contains(search)) {
                                displayList.add(items)
                            }
                            recyclerView.adapter!!.notifyDataSetChanged()
                        }
                    } else {
                        displayList.clear()
                        displayList.addAll(itemsList)
                        recyclerView.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }
            })

        }
        return super.onCreateOptionsMenu(menu)
    }


}