package com.example.a487_project.Classes

import java.util.Collections //Boran

object  CategorySys {
    lateinit var categoryItems: ArrayList<Category>
        private set

    fun prepareData(){
        categoryItems = ArrayList()
        Collections.addAll<Category>(
            categoryItems,
            Category("Accessories"),
            Category("Dresses"),
            Category("Hairs"),
            Category("Shoes"),
            Category("Tops"),
            Category("Face") //eyes, lips, makeup

        )
    }

    fun addItem(sm: Category){
        categoryItems!!.add(sm)
    }
}