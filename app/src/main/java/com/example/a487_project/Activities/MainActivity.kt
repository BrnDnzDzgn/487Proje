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
                val selectedTheme = ThemeSys.themesList.get(pos)
                binding.imageView.setImageResource(getImageResourceForTheme(selectedTheme))
            }
            override fun onNothingSelected(parent: AdapterView<*>?){}
        })

    }

    private fun getImageResourceForTheme(themes: Themes): Int{
        return when (themes.name){
            "fantasy" -> R.drawable.fantasy_icon
            "gothic" -> R.drawable.gothic_icon
            "office" -> R.drawable.office_icon
            else -> R.drawable.ic_launcher_foreground
        }
    }

    private fun displayToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}