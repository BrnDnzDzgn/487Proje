package com.example.a487_project.Classes

object ClothingSys {
    //keeps small icon of clothings in recycler view
    lateinit var clothings: ArrayList<ClothingItemKami>

    init {
        CategorySys.prepareData() // Call prepareData within an initialization block
        clothings = ArrayList()
    }

    val categories = CategorySys.categoryItems

    //to fill recycler view with clothings according to category
    fun generateClothingItems(category: String) {
        val itemCount = categories.find { it.category == category }?.itemCount ?: 0

        when (category) {
            "Accessory" -> {
                for (j in 1..itemCount) {
                    val itemName = "acs$j"
                    clothings.add(ClothingItemKami(7, "Default", itemName, "Accessory"))
                }
            }
            "Top" -> {
                for (j in 1..itemCount) {
                    val itemName = "top$j"
                    clothings.add(ClothingItemKami(6, "Default", itemName, "Top"))
                }
            }
            "Bottom" -> {
                for (j in 1..itemCount) {
                    val itemName = "bottom$j"
                    clothings.add(ClothingItemKami(5, "Default", itemName, "Bottom"))
                }
            }
            "Shoes" -> {
                for (j in 1..itemCount) {
                    val itemName = "shoes$j"
                    clothings.add(ClothingItemKami(3, "Default", itemName, "Shoes"))
                }
            }
            "Dress" -> {
                for (j in 1..itemCount) {
                    val itemName = "dress$j"
                    clothings.add(ClothingItemKami(4, "Default", itemName, "Dress"))
                }
            }
            "Hair front", "Hair back" -> {
                for (j in 1..itemCount) {
                    val itemName = "hair$j"
                    clothings.add(ClothingItemKami(1, "Default", itemName, "Hair front"))
                }
            }
            // Handle unknown categories here, if needed
        }
    }

    fun cleanClothings() {
        clothings.clear()
    }


}
