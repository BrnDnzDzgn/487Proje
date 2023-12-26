package com.example.a487_project.Database


import com.example.a487_project.Classes.Look
import com.example.a487_project.Classes.ThemewDesc
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("UHY7") //This string added to the base URL
    // https://www.jsonkeeper.com/b/UHY7 go to this link to see the json
    fun getParentJSONObject(): Call<List<ThemewDesc>>

}