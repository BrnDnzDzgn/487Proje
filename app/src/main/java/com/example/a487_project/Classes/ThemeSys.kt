package com.example.a487_project.Classes

import com.example.a487_project.R
import java.util.Collections

class ThemeSys {
    companion object { //Boran
        var themesList = ArrayList<Themes>()
        fun prepare(){
            themesList.clear()
            Collections.addAll(
                themesList,
                Themes("gothic", R.drawable.gothic_icon),
                Themes("fantasy", R.drawable.fantasy_icon),
                Themes("office", R.drawable.office_icon)
            )
        }
    }
}