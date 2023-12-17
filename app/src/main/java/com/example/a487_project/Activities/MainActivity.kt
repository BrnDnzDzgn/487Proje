package com.example.a487_project.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import com.example.a487_project.Classes.ThemeSys
import com.example.a487_project.Classes.Themes
import com.example.a487_project.CustomAdapters.CustomSpinnerAdapter
import com.example.a487_project.R
import com.example.a487_project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() { //Boran
    lateinit var binding: ActivityMainBinding
    private lateinit var themeItems: ArrayList<Themes>
    lateinit var spinner: Spinner
    var pos = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ThemeSys.prepare()

        themeItems = ThemeSys.themesList
        spinner = findViewById<Spinner>(R.id.spinner)

        binding.goFashion.setOnClickListener {
            val switchActivityIntent = Intent(this, FashionRoomActivity::class.java)
            startActivity(switchActivityIntent)
        }


        binding.goFashion.setOnClickListener {
            val switchActivityIntent = Intent(this, FashionRoomActivity::class.java)
            startActivity(switchActivityIntent)
        }

        val adapter = CustomSpinnerAdapter(this, themeItems)
        spinner.setAdapter(adapter)

        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                pos = position
                if (themeItems.isNotEmpty()) {
                    val selectedTheme = themeItems[position]
                    binding.imageView.setImageResource(getImageResourceForTheme(selectedTheme))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                displayToast("Nothing selected")
            }
        })
    }

    private fun getImageResourceForTheme(themes: Themes): Int {
        return themes.imgId
    }


    private fun displayToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}