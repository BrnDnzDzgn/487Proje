package com.example.a487_project.Classes

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.a487_project.R
import java.io.IOException

class CustomWorker(var context: Context, var workerParams: WorkerParameters) : Worker(context, workerParams) {
    private val tagForLogcat = "WorkerEx"
    private var mediaPlayer: MediaPlayer? = null

    override fun doWork(): ListenableWorker.Result {
        // Get input data
        val num: Int = inputData.getInt("num", 1)
        val name: String = inputData.getString("name").toString()

        return try {
            Log.d(tagForLogcat, "doWork Called, inputs: $num $name")

            // Start playing music
            startMusic()

            var sum = 0
            for (i in 1..num) {
                sum += i
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    throw RuntimeException(e)
                }
            }

            // Stop playing music
            stopMusic()

            // Create the output of the worker
            val outputData = Data.Builder().putInt("result", sum).build()
            Utils.sendNotification(context, sum.toString() + "")
            Log.d(tagForLogcat, "End of worker")

            ListenableWorker.Result.success(outputData)
        } catch (throwable: Throwable) {
            Log.e(tagForLogcat, "Error in worker: ${throwable.message}")

            // Stop playing music in case of an error
            stopMusic()

            ListenableWorker.Result.failure()
        }
    }

    private fun startMusic() {
        try {
            mediaPlayer = MediaPlayer.create(context, R.raw.button_click_sound) // Replace with your music file
            mediaPlayer?.setOnCompletionListener { restartMusic() } // Set onCompletionListener
            mediaPlayer?.start()
        } catch (e: IOException) {
            Log.e(tagForLogcat, "Error starting music: ${e.message}")
        }
    }

    private fun stopMusic() {
        mediaPlayer?.apply {
            if (isPlaying) {
                stop()
            }
            release()
        }
    }

    private fun restartMusic() {
        mediaPlayer?.apply {
            stop()
            prepareAsync()
            setOnPreparedListener {
                start()
            }
        }
    }
}
