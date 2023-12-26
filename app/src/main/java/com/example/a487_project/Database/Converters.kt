package com.example.a487_project.Database


import androidx.room.TypeConverter
import com.example.a487_project.Classes.ClothingItemKami
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String?): MutableList<ClothingItemKami>? {
        if (value == null) {
            return null
        }

        val listType = object : TypeToken<MutableList<ClothingItemKami>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: MutableList<ClothingItemKami>?): String? {
        if (list == null) {
            return null
        }

        return Gson().toJson(list)
    }
}