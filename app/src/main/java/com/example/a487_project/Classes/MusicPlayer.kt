package com.example.a487_project.Classes

import android.media.MediaPlayer
import com.example.a487_project.App
import com.example.a487_project.R

class MusicPlayer private constructor() {
    private var mediaPlayer: MediaPlayer? = null

    init {
        mediaPlayer = MediaPlayer.create(App.instance, R.raw.background_music)
        mediaPlayer?.isLooping = false
        mediaPlayer?.setOnCompletionListener {
            mediaPlayer?.start()
        }
    }

    fun start() {
        mediaPlayer?.start()
    }

    fun stop() {
        mediaPlayer?.pause()
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    companion object {
        val instance: MusicPlayer by lazy { MusicPlayer() }
    }
}
