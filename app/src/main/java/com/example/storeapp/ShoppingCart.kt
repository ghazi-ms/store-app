package com.example.storeapp

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
        val items =
            getSharedPreferences("CartItems", Context.MODE_PRIVATE).getString("name", "noitems")
        val qtt =
            getSharedPreferences("CartItems", Context.MODE_PRIVATE).getInt("selectedOption", 0)

        itemsList.add("hp 305 Quntity :" + qtt + " name" + items)
        /*itemsList.add("hp 650")
        itemsList.add("hp 912")
        itemsList.add("hp 65")
        itemsList.add("hp 712")
        itemsList.add("hp 680")
        itemsList.add("Canon 445")
        itemsList.add("Canon 275 & 267")
        itemsList.add("Canon pixma 490")
        itemsList.add("Canon  primium P & PBK & C & M & Y")
        itemsList.add("Canon GL-490 BK & C & M & Y")
        itemsList.add("Canon  BK & C & M & Y")
        itemsList.add("bother lc3217 ")
        itemsList.add("bother lc3719XL BK")
        itemsList.add("bother BI5000C")
        itemsList.add("bother BK & C & M & Y BT")
        itemsList.add("bother LC103Cl XL")*/

        displayList.addAll(itemsList)
        recyclerView = findViewById(R.id.rex)
        recyclerAdapter = RecyclerAdapter(displayList)
        recyclerView.adapter = recyclerAdapter
        /*val uri:String="drawable/hp1"

        val imageResource = resources.getIdentifier(uri, null,"com.example.storeapp")
        findViewById<ImageView>(R.id.Rtxview).set)*/
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