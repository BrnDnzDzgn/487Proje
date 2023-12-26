package com.example.a487_project.Database


import com.example.a487_project.Classes.Look
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("6RER") //This string added to the base URL
    fun getParentJSONObject(): Call<Look>

}