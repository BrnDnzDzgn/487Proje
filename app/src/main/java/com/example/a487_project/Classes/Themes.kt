package com.example.a487_project.Classes

import android.os.Parcel
import android.os.Parcelable

class Themes() : Parcelable {
    var name: String = ""
    var imgId = 0

    constructor(parcel: Parcel) : this() {
        name = parcel.readString().toString()
        imgId = parcel.readInt()
    }

    constructor(name: String, imgId: Int):this(){
        this.name=name
        this.imgId = imgId
    }

    override fun toString(): String {
        return """
            name='$name', imgId='$imgId'
        """.trimIndent()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(imgId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Themes> {
        override fun createFromParcel(parcel: Parcel): Themes {
            return Themes(parcel)
        }

        override fun newArray(size: Int): Array<Themes?> {
            return arrayOfNulls(size)
        }
    }

}