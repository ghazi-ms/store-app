package com.example.storeapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment


class CustomDialogFragment(var name : String) : DialogFragment(R.layout.fragment_dialog_custom) {
    var itemsList = hashSetOf<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var selectedOption: Int
        val uri: String = "drawable/" + name

        val imageResource = resources.getIdentifier(uri, null, "com.example.storeapp")
        view.findViewById<ImageView>(R.id.productView).setImageResource(imageResource)
        val numberPicker: NumberPicker = view.findViewById(R.id.numberPicker)
        val buttonCancel: Button = view.findViewById(R.id.buttonCancel)
        val buttonAdd: Button = view.findViewById(R.id.buttonAdd)
        numberPicker.minValue = 1
        numberPicker.maxValue = 20


        buttonCancel.setOnClickListener {
            dismiss()
        }

        buttonAdd.setOnClickListener {
            try {

                selectedOption = numberPicker.value.toInt()

                itemsList.add("Iteam " + name + " quantity" + selectedOption)

                val x = requireActivity().getSharedPreferences("CartItems", Context.MODE_PRIVATE)
                    .getStringSet("list", itemsList) as HashSet<String>

                if (x != itemsList) {
                    x.add(itemsList.toString())
                }
                requireActivity().getSharedPreferences("CartItems", Context.MODE_PRIVATE).edit()
                    .apply {
                        putString("name", name)
                        putInt("selectedOption", selectedOption)
                        if (x.isEmpty()) {
                            putStringSet("list", itemsList)
                        } else putStringSet("list", x)
                    }.apply()

                dismiss()
            } catch (exp: Exception) {
                Log.d(exp.toString(), "wwwwwwwwwww")

            }
        }

    }
}


