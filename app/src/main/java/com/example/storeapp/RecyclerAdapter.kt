package com.example.storeapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(itemsList: MutableList<String>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var itemsList: MutableList<String> = itemsList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = itemsList[position]
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var cardView: CardView
        lateinit var itemTitle: TextView

        init {

            cardView = itemView.findViewById(R.id.card_view)
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                Toast.makeText(
                    itemView.context,
                    "You have selected ${itemsList[position]}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}


