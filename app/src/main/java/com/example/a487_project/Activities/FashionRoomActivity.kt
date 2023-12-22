package com.example.a487_project.Activities

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a487_project.Classes.Category
import com.example.a487_project.Classes.ClothingItemKami
import com.example.a487_project.Classes.ClothingSys
import com.example.a487_project.Classes.Themes
import com.example.a487_project.CustomAdapters.ClothingListAdapther
import com.example.a487_project.CustomAdapters.CustomRecyclerViewAdapter
import com.example.a487_project.R
import com.example.a487_project.databinding.ActivityFashionRoomBinding

class FashionRoomActivity : AppCompatActivity() { //Kamila
    lateinit var binding: ActivityFashionRoomBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CustomRecyclerViewAdapter
    lateinit var categoryList: ArrayList<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Recycle View Categories
        binding=ActivityFashionRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        // Set RecyclerView optimization
        recyclerView.setHasFixedSize(true);


        categoryList = arrayListOf(
            Category("Top", 5),
            Category("Bottom", 5),
            Category("Dress", 5),
            Category("Shoes", 5),
            Category("Accessory", 10),
            Category("Hair front", 5),
            Category("Hair back", 5)
        )
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = CustomRecyclerViewAdapter(this, categoryList)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter


        // Recycle View Clothings

        val recyclerView2: RecyclerView = findViewById(R.id.recyclerViewClothes)
        // Set RecyclerView optimization
        recyclerView2.setHasFixedSize(true);
        recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val items = mutableListOf(
            ClothingItemKami(4, "Red", "top1", "Top"),
            ClothingItemKami(4, "Blue", "top2", "Top"),
            // Add more items as needed
        )

        val adapter2 = ClothingListAdapther(items)
        recyclerView2.adapter = adapter2

        //adapther1 click
        adapter.setAdapter2(adapter2)






        // to retrieve the selected theme information from the intent
        val selectedTheme = intent.getParcelableExtra<Themes>("SELECTED_THEME")

        //use these
        val themeName = selectedTheme?.name
        val themeImgId = selectedTheme?.imgId


        binding.goMain.setOnClickListener {
            finish()
        }



    }
}