package com.example.a487_project

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.a487_project.Classes.Themes

class CustomSpinnerAdapter(var context: Context, var spinnerItemValues: ArrayList<Themes>):
ArrayAdapter<Themes>(context, R.layout.spinner_item_layout, spinnerItemValues) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View{
        return getCustomView(position, convertView, parent)
    }

    override fun getDropDownView( position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View{
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflator.inflate(R.layout.spinner_item_layout, parent, false)

        val constraintLayout = view.findViewById<ConstraintLayout>(R.id.itemConstraintLayout)
        val imgItem = view.findViewById<ImageView>(R.id.imgItemSocial)

        return view
    }
}
