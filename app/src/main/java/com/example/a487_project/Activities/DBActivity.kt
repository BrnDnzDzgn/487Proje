package com.example.a487_project.Activities

import android.content.Intent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.a487_project.Classes.Category
import com.example.a487_project.Classes.Look
import com.example.a487_project.CustomAdapters.CustomRecyclerViewAdapter
import com.example.a487_project.Database.ApiClient
import com.example.a487_project.Database.ApiService
import com.example.a487_project.R
import com.example.a487_project.databinding.ActivityDbactivityBinding
import com.example.a487_project.databinding.ActivityFashionRoomBinding

class DBActivity : AppCompatActivity() {
    lateinit var binding: ActivityDbactivityBinding
    lateinit var recipeService: ApiService
    lateinit var look: Look
    override fun onCreate(savedInstanceState: Bundle?) {

        recipeService = ApiClient.getClient().create(ApiService::class.java) // By that reference retrofit understands which requests will be sent to server
        var request = recipeService.getParentJSONObject()


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dbactivity)
        binding = ActivityDbactivityBinding.inflate(layoutInflater)

        binding.goback2btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        /*Log.d("JSONARRAYPARSE", "Before Request")

        request.enqueue(object : Callback<Look> {
            override fun onFailure(call: Call<Look>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
                Log.d("JSONARRAYPARSE", "Error: "+t.message.toString())
            }
            override fun onResponse(call: Call<Look>, response: Response<Look>) {
                Log.d("JSONARRAYPARSE", "Response taken"+response.body().toString())
                if (response.isSuccessful) {
                    look = (response.body() as Look?)!!
                    Log.d("JSONARRAYPARSE", "Parent taken from server"+parent.toString())
                    binding.dbdisplaytv.text = look.toString()
                }
            }
        })*/

    }
}