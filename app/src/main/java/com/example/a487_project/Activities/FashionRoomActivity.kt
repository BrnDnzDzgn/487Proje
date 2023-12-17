package com.example.a487_project.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a487_project.databinding.ActivityFashionRoomBinding

class FashionRoomActivity : AppCompatActivity() { //Kamila
    lateinit var binding: ActivityFashionRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityFashionRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goMain.setOnClickListener {
            finish()
        }

    }
}