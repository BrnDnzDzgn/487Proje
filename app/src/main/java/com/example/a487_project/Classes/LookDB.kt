package com.example.a487_project.Classes

import android.view.View
import android.widget.ImageView
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.a487_project.util.Constants
import kotlin.random.Random
@Entity(tableName = Constants.TABLENAME)
class LookDB(
    @PrimaryKey var id: Int = 0,
    var body: String = "no",
    var shoe: String = "no",
    var hair: String = "no",
    var bottom: String = "no",
    var top: String = "no",
    var dress: String = "no",
    var acc: String = "no",
    var eye: String = "no",
    var lip: String = "no",
    var shownCounter: Int = 0,
    var likedCounter: Int = 0,
    var timeCounter: Int = 0,
    var theme: String = "no theme"
) {
    override fun toString(): String {
        return "LookDB(id=$id, body='$body', shoe='$shoe', hair='$hair', " +
                "bottom='$bottom', top='$top', dress='$dress', acc='$acc', " +
            "likedCounter=$likedCounter, timeCounter=$timeCounter, " +
                    "theme='$theme')"
        }

    }