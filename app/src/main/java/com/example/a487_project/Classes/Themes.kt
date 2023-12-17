package com.example.a487_project.Classes

class Themes {
    private var name: String? = null
    private var imgId = 0

    constructor(name: String?, imgId: Int) {
        this.name = name
        this.imgId = imgId
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getImgId(): Int {
        return imgId
    }

    fun setImgId(imgId: Int) {
        this.imgId = imgId
    }
}