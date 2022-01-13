package com.example.storeapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment


class CustomDialogFragment(var name : String) : DialogFragment(R.layout.fragment_dialog_custom) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var selectedOption: Int
        val uri: String = "drawable/" + name

        val imageResource = resources.getIdentifier(uri, null, "com.example.storeapp")
        view.findViewById<ImageView>(R.id.productView).setImageResource(imageResource)
        val numberPicker: NumberPicker = view.findViewById(R.id.numberPicker)
        val buttonCancel: Button = view.findViewById(R.id.buttonCancel)
        val buttonAdd: Button = view.findViewById(R.id.buttonAdd)
        numberPicker.minValue = 0
        numberPicker.maxValue = 20


        buttonCancel.setOnClickListener {
            dismiss()
        }

        buttonAdd.setOnClickListener {
            selectedOption = numberPicker.value.toInt()
            requireActivity().getSharedPreferences("CartItems", Context.MODE_PRIVATE).edit().apply {
                putString("name", name)
                putInt("selectedOption", selectedOption)
            }.apply()
            requireActivity().run {
                startActivity(Intent(this, ShoppingCart::class.java))
                finish()
            }
            dismiss()
        }

    }
}


