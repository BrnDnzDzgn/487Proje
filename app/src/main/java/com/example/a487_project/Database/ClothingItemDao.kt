package com.example.a487_project.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ClothingItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClothingItem(clothingItem: ClothingItemEntity)

    @Query("SELECT * FROM clothing_items WHERE category = :category")
    fun getClothingItemsByCategory(category: String): LiveData<List<ClothingItemEntity>>
}
