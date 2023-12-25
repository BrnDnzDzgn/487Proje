// File: ClothingItemDao.kt
package com.example.a487_project.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a487_project.Classes.ClothingItemKami

@Dao
interface ClothingItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClothingItem(clothingItem: ClothingItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllClothingItems(clothingItems: List<ClothingItemEntity>)

    @Query("SELECT * FROM clothing_items")
    suspend fun getAllClothingItems(): List<ClothingItemEntity>

    @Query("SELECT * FROM clothing_items WHERE category = :category")
    suspend fun getClothingItemsByCategory(category: String): List<ClothingItemEntity>

    @Query("DELETE FROM clothing_items")
    suspend fun clearAllClothingItems()
}
