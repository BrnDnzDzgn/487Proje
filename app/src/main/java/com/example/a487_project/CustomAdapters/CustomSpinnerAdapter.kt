package com.example.a487_project.CustomAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.a487_project.Classes.Themes
import com.example.a487_project.R

class CustomSpinnerAdapter(var context: Context, var spinnerItemValues: ArrayList<Themes>):
ArrayAdapter<Themes?>(context, R.layout.spinner_item_layout, spinnerItemValues!! as List<Themes>) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View{
        return getCustomView(position, convertView, parent)
    }

    override fun getDropDownView( position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, convertView, parent)
    }


    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View{
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflator.inflate(R.layout.spinner_item_layout, parent, false)

        val themeName = view.findViewById<TextView>(R.id.spinnerItemTextView)

        val selectedItem = getItem(position)
        themeName.text = selectedItem?.name ?: ""

        return view
    }
}
