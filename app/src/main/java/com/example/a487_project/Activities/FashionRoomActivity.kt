package com.example.a487_project.Activities

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a487_project.Classes.Category
import com.example.a487_project.Classes.ClothingItemKami
import com.example.a487_project.Classes.Themes
import com.example.a487_project.CustomAdapters.ClothingListAdapther
import com.example.a487_project.CustomAdapters.CustomRecyclerViewAdapter
import com.example.a487_project.R
import com.example.a487_project.databinding.ActivityFashionRoomBinding
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.a487_project.Classes.Look


class FashionRoomActivity : AppCompatActivity() { //Kamila
    lateinit var binding: ActivityFashionRoomBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CustomRecyclerViewAdapter
    lateinit var categoryList: ArrayList<Category>
    private lateinit var gestureDetector: GestureDetector

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
            ClothingItemKami(6, "Red", "top1", "Top"),
            ClothingItemKami(6, "Blue", "top2", "Top"),
            ClothingItemKami(6, "Red", "top3", "Top"),
            ClothingItemKami(6, "Blue", "top4", "Top"),
            ClothingItemKami(6, "Red", "top5", "Top"),

            // Add more items as needed
        )

        val adapter2 = ClothingListAdapther(items)
        recyclerView2.adapter = adapter2

        //adapther1 click
        adapter.setAdapter2(adapter2)


        // theme
        val selectedTheme = intent.getParcelableExtra<Themes>("SELECTED_THEME")


        val themeName = selectedTheme?.name?.capitalize() + "Theme"
        val themeTextView = findViewById<TextView>(R.id.theme)
        themeTextView.text = themeName
        if(selectedTheme?.name == "office"){
            themeTextView.setTextColor(Color.BLACK)
            val constraintLayout = findViewById<ConstraintLayout>(R.id.fashionRoomID)
            constraintLayout.setBackgroundResource(R.drawable.office)
        }
        else if(selectedTheme?.name == "fantasy"){
            themeTextView.setTextColor(Color.WHITE)
            val constraintLayout = findViewById<ConstraintLayout>(R.id.fashionRoomID)
            constraintLayout.setBackgroundResource(R.drawable.fantasy2)
        }
        else if(selectedTheme?.name == "gothic"){
            themeTextView.setTextColor(Color.WHITE)
            val constraintLayout = findViewById<ConstraintLayout>(R.id.fashionRoomID)
            constraintLayout.setBackgroundResource(R.drawable.gothic2)
        }






        //gesture
        val randomButton = findViewById<Button>(R.id.Random)

        // Initialize the GestureDetector
        gestureDetector = GestureDetector(this, object : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent): Boolean {
                // Show a toast message when double-tapped
                val myLook = Look()
                myLook.randomizeLook()
                val rootView = findViewById<View>(android.R.id.content)
                myLook.setLookToBody(rootView)
                return true
            }
        })

        // Set an onTouchListener to the button
        randomButton.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
            true
        }

        val goMainButton = findViewById<Button>(R.id.goMain)

        // Set an OnClickListener for the button
        goMainButton.setOnClickListener {
            // Create an Intent to start the main activity
            val intent = Intent(this, MainActivity::class.java)

            // Start the main activity
            startActivity(intent)
        }
    }






}
