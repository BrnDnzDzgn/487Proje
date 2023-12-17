package com.example.a487_project.Classes

import com.example.a487_project.R
import java.util.Collections

class ThemeSys {
    companion object {
        var themesList = ArrayList<Themes>()
        fun prepare(){
            themesList.clear()
            Collections.addAll(
                themesList,
                Themes("gothic", R.drawable.gothic),
                Themes("fantasy", R.drawable.fantasy),
                Themes("office", R.drawable.office)
            )
        }
    }
}