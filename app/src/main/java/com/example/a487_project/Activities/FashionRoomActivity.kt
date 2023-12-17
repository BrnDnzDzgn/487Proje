package com.example.a487_project.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a487_project.databinding.ActivityFashionRoomBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.a487_project.R
import com.example.a487_project.CustomAdapters.CustomRecyclerViewAdapter
import com.example.a487_project.Classes.Category

class FashionRoomActivity : AppCompatActivity() { //Kamila
    lateinit var binding: ActivityFashionRoomBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CustomRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityFashionRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView

        // Add categories "A," "B," and "C" to yourCategoryList
        val categoryList: ArrayList<com.example.a487_project.Classes.Category> = arrayListOf(
            com.example.a487_project.Classes.Category("A"),
            com.example.a487_project.Classes.Category("B"),
            com.example.a487_project.Classes.Category("C"),
            com.example.a487_project.Classes.Category("D"),
        )

        adapter = CustomRecyclerViewAdapter(this, categoryList)
        adapter.setHorizontalLayoutManager(recyclerView)
        recyclerView.adapter = adapter

        binding.goMain.setOnClickListener {
            finish()
        }



    }
}