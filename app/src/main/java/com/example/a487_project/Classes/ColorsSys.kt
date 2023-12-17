package com.example.a487_project.Classes

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode

class ColorsSys {

    val colors = mutableMapOf(
        "purple" to "#680094",
        "cyan" to "#deb6f0",
        "brown" to "#59210e",
        "red" to "#ff1f1f",
        "blue" to "#347deb"
    )

    val colors2 = hashMapOf(
        "pink" to Pair("#ff0363", "#5ea4ff"),
        "red-yellow" to Pair("#ff0303", "#b5f0a8"),
        "weird green-gray" to Pair("#080847", "#a6ffde"),
        "weird yellow-gray" to Pair("#02002e", "#d0e364"),
        "pink-brown" to Pair("#361916", "#de9a95"),
        "purple" to Pair("#680094", "#deb6f0")
    )

    // Shader for one color
    fun applyTint(originalBitmap: Bitmap, tintColor: Int): Bitmap {
        val width = originalBitmap.width
        val height = originalBitmap.height

        val tintedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(tintedBitmap)
        val paint = Paint()
        val colorMatrix = ColorMatrix()

        val r = Color.red(tintColor) / 255f
        val g = Color.green(tintColor) / 255f
        val b = Color.blue(tintColor) / 255f
        colorMatrix.set(floatArrayOf(
            r, 0f, 0f, 0f, 0f,
            0f, g, 0f, 0f, 0f,
            0f, 0f, b, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        ))

        paint.colorFilter = ColorMatrixColorFilter(colorMatrix)
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)

        return tintedBitmap
    }

    // For two colors
    fun applyTint2(originalBitmap: Bitmap, tint1: Int, tint2: Int): Bitmap {
        val resultBitmap = Bitmap.createBitmap(
            originalBitmap.width,
            originalBitmap.height,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(resultBitmap)

        val paint = Paint()

        val colorMatrix = ColorMatrix()
        colorMatrix.setSaturation(0f)
        val grayscaleFilter = ColorMatrixColorFilter(colorMatrix)
        paint.colorFilter = grayscaleFilter

        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)
        val alphaBitmap = originalBitmap.extractAlpha()

        paint.colorFilter = null
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DARKEN)
        paint.color = tint2
        canvas.drawBitmap(alphaBitmap, 0f, 0f, paint)

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.LIGHTEN)
        paint.color = tint1
        canvas.drawBitmap(alphaBitmap, 0f, 0f, paint)

        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        paint.color = tint1
        canvas.drawBitmap(originalBitmap, 0f, 0f, paint)

        return resultBitmap
    }
}
