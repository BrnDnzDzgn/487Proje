package com.example.a487_project.Activities

import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.a487_project.Classes.CustomWorker
import com.example.a487_project.Classes.MusicPlayer
import com.example.a487_project.Classes.ThemeSys
import com.example.a487_project.Classes.Themes
import com.example.a487_project.CustomAdapters.CustomSpinnerAdapter
import com.example.a487_project.R
import com.example.a487_project.databinding.ActivityMainBinding
import com.nineoldandroids.animation.AnimatorListenerAdapter
import com.nineoldandroids.animation.AnimatorSet
import com.nineoldandroids.animation.ObjectAnimator
import androidx.work.BackoffPolicy
import androidx.work.Data
import androidx.work.WorkInfo
import java.util.concurrent.TimeUnit
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var themeItems: ArrayList<Themes>
    lateinit var spinner: Spinner
    var pos = 0

    // for worker that plays music
    lateinit var workManager: WorkManager
    lateinit var workRequest: OneTimeWorkRequest
    lateinit var customWorker: CustomWorker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialize mediaplayer with the background music and restart it after it ends
        val mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val intent = intent
        val notificationId = intent.getIntExtra("notificationID", 0)
        val message = intent.getStringExtra("msg")
        if (message != null) {
            mNotificationManager.cancel(notificationId)
            //binding.tvResult.text =message
            mNotificationManager.cancel(notificationId)
        }

        val constraints: Constraints = Constraints.Builder()
            .build()

        workRequest = OneTimeWorkRequest.Builder(CustomWorker::class.java)
            .setInputData(Data.Builder().putInt("num", 10).putString("boran", "düzgün").build())
            .build()

        workRequest = OneTimeWorkRequestBuilder<CustomWorker>().setBackoffCriteria(
            BackoffPolicy.LINEAR,
            30,
            TimeUnit.MILLISECONDS)
            .build()

        workManager = WorkManager.getInstance(this)

        binding.textView.setOnClickListener(View.OnClickListener {

            workRequest = OneTimeWorkRequest.Builder(CustomWorker::class.java)
                .setInputData(Data.Builder().putInt("num", 10).putString("boran", "düzgün").build())
                .build()

            workManager.enqueue(workRequest)
            Toast.makeText(this@MainActivity, "Worker is working, brain is braining",Toast.LENGTH_SHORT).show()

            workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this@MainActivity,
                Observer{ workInfo ->
                    if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                        val resultData: Data = workInfo.outputData//get output of worker
                        Toast.makeText(this@MainActivity, "SUCCEESS " ,Toast.LENGTH_LONG ).show()
                    }
                })

        })


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

    override fun onDestroy() {
        super.onDestroy()

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
