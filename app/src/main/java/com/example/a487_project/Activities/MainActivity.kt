package com.example.a487_project.Activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a487_project.Classes.MusicPlayer
import com.example.a487_project.Classes.ThemeSys
import com.example.a487_project.Classes.Themes
import com.example.a487_project.CustomAdapters.CustomSpinnerAdapter
import com.example.a487_project.R
import com.example.a487_project.databinding.ActivityMainBinding
import com.nineoldandroids.animation.AnimatorListenerAdapter
import com.nineoldandroids.animation.AnimatorSet
import com.nineoldandroids.animation.ObjectAnimator

class MainActivity : AppCompatActivity() {
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
            val selectedTheme = themeItems[pos]
            val switchActivityIntent = Intent(this, FashionRoomActivity::class.java)
            switchActivityIntent.putExtra("SELECTED_THEME", selectedTheme)
            startActivity(switchActivityIntent)
            MusicPlayer.instance.start()
        }

        binding.themedescbtn.setOnClickListener {
            val intent = Intent(this, ThemeDescActivity::class.java)
            startActivity(intent)
        }

        val adapter = CustomSpinnerAdapter(this, themeItems)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                pos = position
                if (themeItems.isNotEmpty()) {
                    val selectedTheme = themeItems[position]
                    fadeInImage(binding.imageView, getImageResourceForTheme(selectedTheme))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                displayToast("Nothing selected")
            }
        }
    }

    private fun getImageResourceForTheme(themes: Themes): Int {
        return themes.imgId
    }

    private fun displayToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun fadeInImage(imageView: ImageView, imageResId: Int) {
        // Fade in animation
        val alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f)
        alphaAnimator.duration = 1000

        // Set the new image resource after the animation ends
        alphaAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: com.nineoldandroids.animation.Animator?) {
                imageView.setImageResource(imageResId)
            }
        })

        // Play the animations together
        val animatorSet = AnimatorSet()
        animatorSet.play(alphaAnimator)
        animatorSet.start()
    }
}
