package com.example.netflixkotlin

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import com.example.netflixkotlin.databinding.ActivityDetalhesFilmesBinding
import com.example.netflixkotlin.databinding.ActivityVideoBinding

class video : AppCompatActivity() {

    private  lateinit var binding: ActivityVideoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        val videoUrl = Uri.parse("https://firebasestorage.googleapis.com/v0/b/netflix-kotlin-65184.appspot.com/o/THE%20WITCHER%20_%20TRAILER%20FINAL%20_%20NETFLIX.mp4?alt=media&token=0903650e-3442-4ebd-8f5b-af7d7a9f4b47")

        val video_var = binding.videoView
        video_var.setMediaController(MediaController(this))
        video_var.setVideoURI(videoUrl)
        video_var.requestFocus()
        video_var.start()
    }
}