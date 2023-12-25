package com.example.a487_project.Classes

import java.util.Collections //Boran

object  CategorySys {
    lateinit var categoryItems: ArrayList<Category>
        private set

    fun prepareData(){
        categoryItems = ArrayList()
        Collections.addAll<Category>(
            categoryItems,
            Category("Accessory", 10),
            Category("Dress", 5),
            Category("Hair front", 5),
            Category("Hair back", 5),
            Category("Shoes", 5),
            Category("Top", 5),
            Category("Bottom", 5),
            Category("Eyes", 5),
            Category("Lips", 5),
            Category("Makeup", 5),

        )
    }

    fun addItem(sm: Category){
        categoryItems!!.add(sm)
    }
}