package com.example.a487_project.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a487_project.databinding.ActivityFashionRoomBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.a487_project.R
import com.example.a487_project.CustomAdapters.CustomRecyclerViewAdapter
import com.example.a487_project.Classes.Category
import com.example.a487_project.Classes.ClothingItemKami
import com.example.a487_project.CustomAdapters.ClothingListAdapther
import com.example.a487_project.Classes.Themes

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


        categoryList = arrayListOf(
            Category("Top"),
            Category("Bottom"),
            Category("Dress"),
            Category("Shoes"),
            Category("Accessory"),
            Category("Hair front"),
            Category("Hair back")
        )
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        adapter = CustomRecyclerViewAdapter(this, categoryList)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter

        // Recycle View Clothings

        val recyclerView2: RecyclerView = findViewById(R.id.recyclerViewClothes)
        recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val items = mutableListOf(
            ClothingItemKami(1, "Red", "top1"),
            ClothingItemKami(1, "Blue", "top2"),
            // Add more items as needed
        )
        val adapter2 = ClothingListAdapther(items)
        recyclerView2.adapter = adapter2

        adapter2.setOnItemClickListener { position ->
            val selectedItem = items[position]

            // Assuming you have ImageView IDs like layer_item_1, layer_item_2, etc.
            val layerImageViewId = resources.getIdentifier("layer_${selectedItem.layer}", "id", packageName)
            val layerImageView = findViewById<ImageView>(layerImageViewId)

            // Update the image based on the selected item's imageName
            val imageName = selectedItem.imageName
            val imageResourceId = resources.getIdentifier(imageName, "drawable", packageName)
            layerImageView.setImageResource(imageResourceId)
        }

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