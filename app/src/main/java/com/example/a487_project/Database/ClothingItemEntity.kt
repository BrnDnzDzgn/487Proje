package com.example.a487_project.Database


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a487_project.Classes.ClothingItemKami

@Entity(tableName = "clothing_items")
data class ClothingItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val layer: Int,
    val color: String,
    val imageName: String,
    val category: String
) {
    companion object {
        fun fromClothingItemKami(clothingItem: ClothingItemKami): ClothingItemEntity {
            return ClothingItemEntity(
                layer = clothingItem.layer,
                color = clothingItem.color,
                imageName = clothingItem.imageName,
                category = clothingItem.category
            )
        }
    }
}


