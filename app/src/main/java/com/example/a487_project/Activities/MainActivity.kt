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
        // Cancel the notification that we started
        val intent = intent
        val notificationId = intent.getIntExtra("notificationID", 0)
        val message = intent.getStringExtra("msg")
        if (message != null) {
            mNotificationManager.cancel(notificationId)
            //binding.tvResult.text =message
            mNotificationManager.cancel(notificationId)
        }
        /*
        STEP4: Define Constraints if any
        When all constraints are met, work will run
        */
        val constraints: Constraints = Constraints.Builder()
            .build()
        /*
        STEP3: Create work policy: Schedule the work
        OneTimeWorkRequest: worker will be used for just one work
        PeriodicWorkRequest: Worker will be used for periodic work
         */
        //STEP 4 & STEP7, Create work policy and send data as input to the work. How input will be taken , check CustomWorker class
        workRequest = OneTimeWorkRequest.Builder(CustomWorker::class.java)
            .setInputData(Data.Builder().putInt("num", 10).putString("name", "nese").build())
            .build()
        //it will begin immeditaley in the background
        // if constraints are assigned, it will run immeditaley if constraits are met
        //workRequest = OneTimeWorkRequest.Builder(CustomWorker::class.java).setConstraints(constraints).build()

        //STEP 5: To delay work for 10 minutes
        //workRequest = OneTimeWorkRequest.Builder(CustomWorker::class.java).setInitialDelay(10, TimeUnit.MINUTES).build();

        //STEP 6: To retry and backoff policy: in each 10 seconds it will retry, after subsequent attempts exponential it will try as 20,40,80...
        workRequest = OneTimeWorkRequestBuilder<CustomWorker>().setBackoffCriteria(
            BackoffPolicy.LINEAR,
            30,//OneTimeWorkRequest.MIN_BACKOFF_MILLIS
            TimeUnit.MILLISECONDS)
            .build()
        //workRequest = OneTimeWorkRequest.Builder(CustomWorker::class.java).build();
        //var mperidicRequest = PeriodicWorkRequest.Builder(CustomWorker::class.java, 1, TimeUnit.HOURS).build(); // work scheduled to one hour
        //var mperidicRequest = PeriodicWorkRequest.Builder(CustomWorker::class.java, 1, TimeUnit.HOURS, 15, TimeUnit.MINUTES).build(); // work scheduled to one hour

        /*
        STEP 8_1: to un the work, create WorkManager object
         */
        workManager = WorkManager.getInstance(this)

        binding.textView.setOnClickListener(View.OnClickListener {
            //STEP 4 & STEP7, Create work policy and send data as input to the work. How input will be taken , check MyWorker class
            workRequest = OneTimeWorkRequest.Builder(CustomWorker::class.java)
                .setInputData(Data.Builder().putInt("num", 10).putString("name", "nese").build())
                .build()

            /*
            STEP 8-2: to run the work, assign request object to work manager.
           */
            workManager.enqueue(workRequest)
            Toast.makeText(this@MainActivity, "This will immediately executed. It will not wait for the worker result",Toast.LENGTH_SHORT).show()

            //at the end of background task what will be done
            workManager.getWorkInfoByIdLiveData(workRequest.id).observe(this@MainActivity,
                Observer{ workInfo ->
                    if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                        val resultData: Data = workInfo.outputData//get output of worker
                        Toast.makeText(this@MainActivity, "SUCCEEDED " + resultData.getInt("result", 0),Toast.LENGTH_LONG ).show()
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