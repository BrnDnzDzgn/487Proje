package com.example.a487_project.Activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
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
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import com.example.a487_project.Classes.CustomWorker
import com.example.a487_project.Classes.DataHolder
import com.example.a487_project.Classes.Look
import com.example.a487_project.Classes.LookDB
import com.example.a487_project.Database.ApiClient
import com.example.a487_project.Database.ApiService
import com.example.a487_project.Database.LookDBViewModel
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FashionRoomActivity : AppCompatActivity() { //Kamila
    lateinit var binding: ActivityFashionRoomBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: CustomRecyclerViewAdapter
    lateinit var categoryList: ArrayList<Category>
    private lateinit var gestureDetector: GestureDetector
    lateinit var recipeService: ApiService
    lateinit var look: Look
    private lateinit var lookDBViewModel: LookDBViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        // Recycle View Categories
        binding=ActivityFashionRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)


        recyclerView = binding.recyclerView
        // Set RecyclerView optimization
        recyclerView.setHasFixedSize(true)


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
        recyclerView2.setHasFixedSize(true)
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
        // selectedTheme?.name = intent.getStringExtra("theme").toString()
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


        //go to main
        val goMainButton = findViewById<Button>(R.id.goBack)

        // Set an OnClickListener for the button
        goMainButton.setOnClickListener {
            // Create an Intent to start the main activity
            val intent = Intent(this, MainActivity::class.java)
            DataHolder.look = null

            // Start the main activity
            startActivity(intent)
        }

        //go to makeup and send category
        binding.gotoMakeupbtn.setOnClickListener {
            val intent = Intent(this, MakeUpRoomActivity::class.java)
            /*var look : Look
            look = createLookFromCurrentLook()
            DataHolder.look = look*/
            intent.putExtra("theme",selectedTheme?.name)
            startActivityForResult(intent, REQUEST_MAKEUP_ROOM)
        }

        //submit btn


        // When you assign a drawable to an ImageView, also set a tag with the resource name.

        lookDBViewModel = ViewModelProvider(this).get(LookDBViewModel::class.java)

        var submitButton = findViewById<Button>(R.id.submitLook)
        submitButton.setOnClickListener {
            var customerToAdd = LookDB(0,"no","no","no","no","no","no","no","no","no",0,0,0,"no theme")

            lookDBViewModel.addLook(customerToAdd)
            Snackbar.make(it, "Look inserted", Snackbar.LENGTH_LONG).show()
            createLookFromCurrentLook()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_MAKEUP_ROOM && resultCode == RESULT_OK) {
            // Handle the result or call a function in response to the finish() in MakeUpRoomActivity
            setChangedFace(DataHolder.eye, DataHolder.lip, DataHolder.makeup)
        }
    }

    companion object {
        const val REQUEST_MAKEUP_ROOM = 123 // You can use any unique request code
    }

    // Function to convert drawable to resource name using the tag
    fun convertDrawableToName(imageView: ImageView): String {
        val tag = imageView.tag
        if (tag is String) {
            return tag
        }
        throw IllegalArgumentException("Drawable resource name is not stored in the tag.")
    }

    fun getCategoryForLayer(layer: Int): String {
        return when (layer) {
            1 -> "Hair front"
            2 -> "Hair back"
            3 -> "Eyes"
            4 -> "Lips"
            5 -> "Makeup"
            6 -> "Shoes"
            7 -> "Dress"
            8 -> "Bottom"
            9 -> "Top"
            10 -> "Accessory"
            else -> "FUCK THIS"}

    }

    // Updated function to create ClothingItemKami objects
    fun createLookFromCurrentLook(): Look {
        var curLook = Look()

        // Array of the layer ids to iterate over
        val layerIds = arrayOf("layer1", "layer2", "layer3", "layer4", "layer5", "layer6", "layer7")
        for (layerId in layerIds) {
            val imageViewId = resources.getIdentifier(layerId, "id", packageName)
            val imageView = findViewById<ImageView>(imageViewId)

            // Retrieve the drawable name from the tag
            try {
                val imageName = convertDrawableToName(imageView)
                val layer = layerId.removePrefix("layer").toInt() // Assuming layerId is like "layer1", "layer2", etc.
                val category = getCategoryForLayer(layer)

                // Create the ClothingItemKami object
                val clothingItemKami = ClothingItemKami(layer,"Default", imageName, category)
                curLook.lookClothings.add(clothingItemKami)
            } catch (e: IllegalArgumentException) {
                // Handle the case where the drawable name could not be retrieved
                e.printStackTrace()
            }
        }

        return curLook
    }


    //to change the face after coming from makeup room
    fun setChangedFace(eye: Int, lip: Int, makeup: Int) {
        when(eye){
            1 -> {
                binding.layer12.setImageResource(R.drawable.eye1)
                binding.layer11.setImageResource(R.drawable.eyeball1)
            }
            2 -> {
                binding.layer12.setImageResource(R.drawable.eye2)
                binding.layer11.setImageResource(R.drawable.eyeball2)
            }
            3 -> {
                binding.layer12.setImageResource(R.drawable.eye3)
                binding.layer11.setImageResource(R.drawable.eyeball3)
            }
        }
        when(lip){
            1 -> {
                binding.layer9.setImageResource(R.drawable.lip1)
                binding.layer10.setImageResource(R.drawable.lipdetail1)
            }
            2 -> {
                binding.layer9.setImageResource(R.drawable.lip2)
                binding.layer10.setImageResource(R.drawable.lipdetail2)
            }
            3 -> {
                binding.layer9.setImageResource(R.drawable.lip3)
                binding.layer10.setImageResource(R.drawable.lipdetail3)
            }
        }

    }

}




