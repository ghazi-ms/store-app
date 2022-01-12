package com.example.storeapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.NumberPicker
import androidx.fragment.app.DialogFragment


class CustomDialogFragment(var name : String) : DialogFragment(R.layout.fragment_dialog_custom) {

   /* fun newInstance(productname: String) {
       /* val f = CustomDialogFragment()

        // Supply num input as an argument.
        val args = Bundle()
        args.putString("name", productname)
        f.setArguments(args)
        return f*/
        name = productname
    }*/
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val uri:String="drawable/"+name
        val imageResource = resources.getIdentifier(uri,null,"com.example.storeapp")
        view.findViewById<ImageView>(R.id.productView).setImageResource(imageResource)
        val numberPicker : NumberPicker = view.findViewById(R.id.numberPicker);
        val buttonCancel : Button = view.findViewById(R.id.buttonCancel);
        val buttonAdd : Button = view.findViewById(R.id.buttonAdd);
        buttonCancel.setOnClickListener{
            dismiss()
        }
        buttonAdd.setOnClickListener {
            val selectedOption: Int = numberPicker.getValue()
            // TODO( return selected choice )
    dismiss()
        }
    }
}


