package com.example.a487_project.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a487_project.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() { //Boran
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goFashion.setOnClickListener {
            val switchActivityIntent = Intent(this, FashionRoomActivity::class.java)
            startActivity(switchActivityIntent)
        }

    }


}