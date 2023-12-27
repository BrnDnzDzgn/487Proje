package com.example.a487_project.Activities

import android.app.NotificationManager
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.a487_project.Classes.CustomWorker
import com.example.a487_project.Classes.DataHolder
import com.example.a487_project.Classes.MusicPlayer
import com.example.a487_project.Classes.ThemeSys
import com.example.a487_project.Classes.Themes
import com.example.a487_project.CustomAdapters.CustomSpinnerAdapter
import com.example.a487_project.R
import com.example.a487_project.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest

class MainActivity : AppCompatActivity() { //Boran
    lateinit var binding: ActivityMainBinding

    // for worker that plays music
    lateinit var workManager: WorkManager
    lateinit var workRequest: OneTimeWorkRequest
    lateinit var customWorker: CustomWorker



    private lateinit var themeItems: ArrayList<Themes>
    lateinit var spinner: Spinner
    var pos = 0




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
        spinner.setAdapter(adapter)

        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                pos = position
                if (themeItems.isNotEmpty()) {
                    val selectedTheme = themeItems[position]
                    binding.imageView.setImageResource(getImageResourceForTheme(selectedTheme))
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                displayToast("Nothing selected")
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    private fun getImageResourceForTheme(themes: Themes): Int {
        return themes.imgId
    }

    private fun displayToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}