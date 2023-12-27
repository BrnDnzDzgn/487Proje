package com.example.a487_project.Classes


import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters

//STE2-1: implement constructor and call base class constructor
class CustomWorker(var context: Context, var workerParams: WorkerParameters):Worker (context, workerParams){
    val tagforLogcat = "WorkerEx"
    /**
     * Override doWork() method to do your actual background processing.  This method is called on a
     * background thread - you are required to **synchronously** do your work and return the
     * Result from this method.
     * Once you return from this method, the Worker is considered to have finished what its doing and will be destroyed.*
     * A Worker is given a maximum of ten minutes to finish its execution and return a Result. After this time has expired, the Worker will
     * be signalled to stop. Result of the computation; note that
     * dependent work will not execute if you use Result.failure()
     *
     * Result.sucess(): indicate whether the work is finished successfully with the Result. Informs the WorkManager service whether the work succeeded.
     * Result.failure(): Informs the WorkManager service whether the work failured.
     * Result.retry(): the work failured and should be tried at another time according to its retry policy
     */
    override fun doWork(): ListenableWorker.Result {
        //STEP 7: get input
        val num: Int = getInputData().getInt("num", 1)
        val name: String = getInputData().getString("name").toString()
        //context = getApplicationContext();
        return try {
            Log.d(tagforLogcat, "doWork Called, inputs: $num $name")
            var sum = 0
            for (i in 1..num) {
                sum += i
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    throw RuntimeException(e)
                }
            }
            //create the output of worker
            val outputData = Data.Builder().putInt("result", sum).build()
            Utils.sendNotification(context, sum.toString() + "")
            Log.d(tagforLogcat, "End of worker")

            ListenableWorker.Result.success(outputData) //this will return
        } catch (throwable: Throwable) {
            Log.d(tagforLogcat, "Error Sending Notification" + throwable.message)

            ListenableWorker.Result.failure() //this will return
        }
    }
}