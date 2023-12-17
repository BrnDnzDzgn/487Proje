package com.example.a487_project.Activities

import android.content.Intent
import android.content.res.Resources.Theme
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.example.a487_project.Classes.CategorySys
import com.example.a487_project.Classes.Themes
import com.example.a487_project.CustomSpinnerAdapter
import com.example.a487_project.R
import com.example.a487_project.databinding.ActivityMainBinding
import java.util.Collections

class MainActivity : AppCompatActivity() { //Boran
    lateinit var binding: ActivityMainBinding
    lateinit var themeItems: ArrayList<Themes>
    lateinit var spinner: Spinner
    var pos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        spinner = findViewById<Spinner>(R.id.spinner)

        binding.goFashion.setOnClickListener {
            val switchActivityIntent = Intent(this, FashionRoomActivity::class.java)
            startActivity(switchActivityIntent)
        }

        val adapter = CustomSpinnerAdapter(this, themeItems)
        spinner.setAdapter(adapter)

        spinner.setOnItemSelectedListener(object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                pos = position
                val selectedTheme = CategorySys.categoryItems.get(pos)
                binding.imageView.setImageResource(getImageResourceFor)
            }
            override fun onNothingSelected(parent: AdapterView<*>?){}
        })

    }

    private fun getImageResourceForTheme(theme: Theme): Int{
        return when (theme.name){
            "fantasy" -> R.drawable.
        }
    }


}