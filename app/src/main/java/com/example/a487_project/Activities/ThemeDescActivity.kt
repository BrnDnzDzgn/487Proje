package com.example.a487_project.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.a487_project.Classes.ThemewDesc
import com.example.a487_project.Database.ApiClient
import com.example.a487_project.Database.ApiService
import com.example.a487_project.R
import com.example.a487_project.databinding.ActivityDbactivityBinding
import com.example.a487_project.databinding.ActivityMainBinding
import com.example.a487_project.databinding.ActivityThemeDescBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ThemeDescActivity : AppCompatActivity() {

    lateinit var binding: ActivityThemeDescBinding
    lateinit var recipeService: ApiService
    lateinit var themewdesc : ThemewDesc
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeDescBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeService = ApiClient.getClient().create(ApiService::class.java) // By that reference retrofit understands which requests will be sent to server
        var request = recipeService.getParentJSONObject()
        /*
        binding.themedescimg1.setImageResource(R.drawable.gothic_icon)
        binding.themedescimg2.setImageResource(R.drawable.fantasy_icon)
        binding.themedescimg3.setImageResource(R.drawable.office_icon)*/



        Log.d("JSONARRAYPARSE", "Before Request")
        request.enqueue(object : Callback<List<ThemewDesc>> {
            override fun onFailure(call: Call<List<ThemewDesc>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
                Log.d("JSONARRAYPARSE", "Error: " + t.message.toString())
                binding.themedesctv1.setText("Error: " + t.message.toString())
            }

            override fun onResponse(call: Call<List<ThemewDesc>>, response: Response<List<ThemewDesc>>) {
                Log.d("JSONARRAYPARSE", "Response taken" + response.body().toString())
                if (response.isSuccessful) {
                    val themewDescList = response.body()
                    if (themewDescList != null && themewDescList.isNotEmpty()) {
                        // Assuming you want to display the first ThemewDesc
                        val themewdesc1 = themewDescList[0]
                        val themewdesc2 = themewDescList[1]
                        val themewdesc3 = themewDescList[2]
                        Log.d("JSONARRAYPARSE", "Parent taken from server" + themewdesc1.toString())
                        Log.d("JSONARRAYPARSE", "Parent taken from server" + themewdesc2.toString())
                        Log.d("JSONARRAYPARSE", "Parent taken from server" + themewdesc3.toString())
                        var strr : String = themewdesc1.toString() + themewdesc2.toString() + themewdesc3.toString()
                        binding.themedesctv1.setText(strr)
                        /*binding.themedesctv1.text = themewdesc1.toString()
                        binding.themedesctv2.text = themewdesc2.toString()
                        binding.themedesctv3.text = themewdesc3.toString()*/
                    } else {
                        Toast.makeText(applicationContext, "List is empty or null", Toast.LENGTH_LONG).show()
                    }
                }

            }
        })

        /*val apiService = RetrofitClient.retrofit.create(ApiService::class.java)
        val request: Call<Look> = apiService.getParentJSONObject()*/

        binding.gobacktomainbtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}