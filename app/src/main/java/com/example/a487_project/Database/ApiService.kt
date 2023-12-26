package com.example.a487_project.Database


import com.example.a487_project.Classes.Look
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("ELLH") //This string added to the base URL
    // https://www.jsonkeeper.com/b/ELLH go to this link to see the json
    fun getParentJSONObject(): Call<Look>

}